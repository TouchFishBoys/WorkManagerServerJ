package com.my.workmanagement.service;

import com.my.workmanagement.entity.*;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.FinalWorkBO;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.model.bo.TopicInfoBO;
import com.my.workmanagement.repository.*;
import com.my.workmanagement.service.interfaces.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final CourseSelectionRepository courseSelectionRepository;
    private final TopicRepository topicRepository;
    private final NormalWorkRepository normalWorkRepository;
    private final TeacherRepository teacherRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public CourseServiceImpl(
            CourseRepository courseRepository,
            TeacherRepository teacherRepository,
            TopicRepository topicRepository,
            NormalWorkRepository normalWorkRepository,
            CourseSelectionRepository courseSelectionRepository,
            TeamRepository teamRepository
    ) {
        this.courseRepository = courseRepository;
        this.courseSelectionRepository = courseSelectionRepository;
        this.topicRepository = topicRepository;
        this.normalWorkRepository = normalWorkRepository;
        this.teacherRepository = teacherRepository;
        this.teamRepository = teamRepository;
    }


    @Override
    public String getCourseName(Integer courseId) throws IdNotFoundException {
        CourseDO course = courseRepository.findByCourseId(courseId);
        if (course == null) {
            throw new IdNotFoundException("courseName");
        }
        return course.getCourseName();
    }

    @Override
    public CourseInfoBO getCourseInfo_Teacher(Integer courseId) throws IdNotFoundException {
        CourseDO courseDO = courseRepository.findByCourseId(courseId);
        if (courseDO == null) {
            throw new IdNotFoundException("course");
        }
        // 取出课程下全部的小组
        List<TeamDO> teams = courseSelectionRepository.findAllByCourseAndTeamIsNotNull(courseDO)
                .stream().map(CourseSelectionDO::getTeam).collect(Collectors.toList());
        // 去除重复项
        List<TeamDO> uniqueTeams = teams.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(TeamDO::getTeamId))), ArrayList::new
                )
        );
        int finishedCount = teamRepository.getFinishedTeams(uniqueTeams);
        logger.debug("Found {} teams under course {}, finished {} teams", uniqueTeams.size(), courseId, finishedCount);

        return CourseInfoBO.CourseInfoBOBuilder.aCourseInfoBO()
                .withCourseId(courseDO.getCourseId())
                .withCourseName(courseDO.getCourseName())
                .withCourseDescription(courseDO.getCourseDescription())
                .withCourseYear(courseDO.getCourseYear())
                .withCourseTeacherName(courseDO.getTeacher().getTeacherName())
                .withStudentCount(courseSelectionRepository.countAllByCourse_CourseId(courseId)) //学生人数
                .withFinishCount(finishedCount) //完成的小组数
                .withTotalCount(uniqueTeams.size()) // 总共的小组数
                .build();
    }

    @Override
    public Integer createCourse(Integer teacherId, String courseName, String courseDescription) throws IdNotFoundException {
        TeacherDO teacher = teacherRepository.findByTeacherId(teacherId);
        if (teacher == null) {
            throw new IdNotFoundException("teacher id");
        }
        CourseDO course = new CourseDO();
        course.setCourseName(courseName);
        course.setCourseDescription(courseDescription);
        course.setTeacher(teacher);
        return courseRepository.save(course).getCourseId();
    }

    @Override
    public CourseInfoBO getCourseInfo_Student(Integer courseId, Integer studentId) throws IdNotFoundException {
        CourseDO courseDO = courseRepository.findByCourseId(courseId);
        if (courseDO == null) {
            throw new IdNotFoundException("course");
        }
        return CourseInfoBO.CourseInfoBOBuilder.aCourseInfoBO()
                .withCourseId(courseDO.getCourseId())
                .withCourseName(courseDO.getCourseName())
                .withCourseDescription(courseDO.getCourseDescription())
                .withCourseYear(courseDO.getCourseYear())
                .withStudentCount(1)
                .withCourseTeacherName(courseDO.getTeacher().getTeacherName())
                .withTotalCount(normalWorkRepository.countAllByTopic_Course_CourseIdAndStudent_StudentId(courseId, studentId))
                .withFinishCount(normalWorkRepository.countAllByTopic_Course_CourseId(courseId))
                .build();
    }

    @Override
    public List<TopicInfoBO> getTopicInfoByCourseId(Integer courseId) throws IdNotFoundException {
        List<TopicDO> topics = topicRepository.findAllByCourse_CourseId(courseId);
        List<TopicInfoBO> topicInfoBOS = new LinkedList<>();
        if (topics == null) {
            throw new IdNotFoundException("topic");
        }
        for (TopicDO topic : topics) {
            topicInfoBOS.add(TopicInfoBO.TopicInfoBOBuilder.aTopicInfoBO()
                    .withTopicId(topic.getTopicId())
                    .withTopicName(topic.getTopicName())
                    .withTopicDescription(topic.getTopicDescription())
                    .withFinishedCount(normalWorkRepository.countAllByTopic_Course_CourseId(courseId))
                    .withTotalCount(topicRepository.countAllByCourse_CourseId(courseId))
                    .withStartTime(topic.getTopicTimeStart())
                    .withFinishTime(topic.getTopicTimeEnd())
                    .build());
        }
        return topicInfoBOS;
    }

    @Override
    public List<StudentInfoBO> getStudentInfo(Integer courseId) throws IdNotFoundException {
        List<CourseSelectionDO> courseSelectionDOS = courseSelectionRepository.findAllByCourse_CourseId(courseId);
        if (courseSelectionDOS == null) {
            throw new IdNotFoundException("courseId:" + courseId.toString());
        }
        List<StudentInfoBO> studentInfoBOS = new LinkedList<>();
        for (CourseSelectionDO courseSelection : courseSelectionDOS) {
            StudentDO student = courseSelection.getStudent();
            studentInfoBOS.add(StudentInfoBO.StudentInfoBOBuilder.aStudentInfoBO()
                    .withStudentClass(student.getStudentClass())
                    .withStudentName(student.getStudentName())
                    .withStudentNum(student.getStudentNum())
                    .build());
        }
        return studentInfoBOS;
    }

    @Override
    public List<FinalWorkBO> getFinalWorkList(Integer courseId) throws IdNotFoundException {
        if (!courseRepository.existsById(courseId)) {
            throw new IdNotFoundException("course id");
        }
        // 取出 （单课学生数）
        List<CourseSelectionDO> courseSelectionList = courseSelectionRepository.findAllByCourse_CourseId(courseId);
        // 去除重复的队伍并取出他们的 FinalWork
        List<FinalWorkDO> teams = courseSelectionList.stream().map(CourseSelectionDO::getTeam).collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(TeamDO::getTeamId))), ArrayList::new
                )
        ).stream().map(TeamDO::getFinalWork).collect(Collectors.toList());
        // TODO: 2021/1/6
        return null;
    }
}
