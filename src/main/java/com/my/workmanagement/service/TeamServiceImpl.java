package com.my.workmanagement.service;

import com.my.workmanagement.entity.CourseSelectionDO;
import com.my.workmanagement.entity.FinalWorkDO;
import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.entity.TeamDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.model.bo.TeamInfoBO;
import com.my.workmanagement.repository.CourseSelectionRepository;
import com.my.workmanagement.repository.StudentRepository;
import com.my.workmanagement.repository.TeamRepository;
import com.my.workmanagement.service.interfaces.StudentService;
import com.my.workmanagement.service.interfaces.TeamService;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
    private final TeamRepository teamRepository;
    private final CourseSelectionRepository courseSelectionRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository,
                           CourseSelectionRepository courseSelectionRepository,
                           StudentRepository studentRepository,
                           StudentService studentService
    ) {
        this.teamRepository = teamRepository;
        this.courseSelectionRepository = courseSelectionRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @Override
    public Integer getTeamId(Integer studentId, Integer courseId) throws IdNotFoundException {
        CourseSelectionDO courseSelection = courseSelectionRepository.findFirstByStudent_StudentIdAndCourse_CourseId(studentId, courseId);
        return courseSelection.getTeam().getTeamId();
    }

    @Override
    public TeamInfoBO getTeamInfo(Integer teamId) throws IdNotFoundException {
        TeamDO team = teamRepository.findByTeamId(teamId);
        TeamInfoBO bo = getBoFromDo(team);
        List<String> name = new LinkedList<>();
        List<StudentInfoBO> students = new LinkedList<>();
        students = studentService.getStudentsByTeamId(teamId);
        for (StudentInfoBO student : students) {
            name.add(student.getStudentName());
        }
        bo.setStudents(name);
        return bo;
    }

    @Override
    public List<TeamInfoBO> getTeamInfoList(Integer courseId) throws IdNotFoundException {
        List<TeamInfoBO> result = new LinkedList<>();
        for (CourseSelectionDO item : courseSelectionRepository.findAllByCourse_CourseId(courseId)) {
            TeamDO team = teamRepository.findByTeamId(item.getTeam().getTeamId());
            result.add(getBoFromDo(team));
        }
        return result;
    }

    @Override
    public List<StudentInfoBO> getTeamMembers(Integer teamId) throws IdNotFoundException {
        List<StudentInfoBO> result = new LinkedList<>();
        if (!teamRepository.existsById(teamId)) {
            throw new IdNotFoundException("teamId");
        }
        courseSelectionRepository.getAllByTeam_TeamId(teamId).forEach(item -> {
            logger.info(item.getStudent().getStudentId().toString());
            StudentDO student = studentRepository.findByStudentId(item.getStudent().getStudentId());
            StudentInfoBO studentInfo = StudentInfoBO.StudentInfoBOBuilder.aStudentInfoBO()
                    .withStudentClass(student.getStudentClass())
                    .withStudentName(student.getStudentName())
                    .withStudentNum(student.getStudentNum())
                    .withStudentId(student.getStudentId())
                    .build();
            result.add(studentInfo);
        });
        return result;
    }

    @Override
    public List<String> getTeamMembersName(Integer teamId) throws IdNotFoundException {
        return getTeamMembers(teamId).stream().map(StudentInfoBO::getStudentName).collect(Collectors.toList());
    }

    @Override
    public Integer getFinalWorkId(Integer teamId) throws IdNotFoundException {
        TeamDO team = teamRepository.findByTeamId(teamId);
        if (team == null) {
            throw new IdNotFoundException("team id");
        }
        return team.getFinalWork().getFworkId();
    }

    public TeamInfoBO getBoFromDo(TeamDO team) throws IdNotFoundException {
        FinalWorkDO finalWork = team.getFinalWork();
        TeamInfoBO bo = new TeamInfoBO();
        if (finalWork == null) {
            bo.setTeamName(team.getTeamName());
            bo.setTeamId(team.getTeamId());
            bo.setMemberCount(getTeamMembers(team.getTeamId()).size());
            return bo;
        }
        bo.setTeamName(team.getTeamName());
        bo.setTeamId(team.getTeamId());
        bo.setMemberCount(getTeamMembers(team.getTeamId()).size());
        bo.setScore(finalWork.getFworkScore());
        bo.setDocumentScore(finalWork.getDocumentScore());
        return bo;
    }

    @Override
    public Integer createTeam(String teamName, Integer studentId, Integer courseId) {
        TeamDO team = new TeamDO();
        Integer teamId = -1;
        team.setTeamName(teamName);
        teamId = teamRepository.save(team).getTeamId();
        joinTeam(studentId, courseId, teamId);
        return teamId;
    }

    @Override
    @Transactional
    public Integer joinTeam(Integer studentId, Integer courseId, Integer teamId) {
        /// TODO: 2021/1/12 finish it
        courseSelectionRepository.setTeamIdByStudentIdAndCourseId(teamId, studentId, courseId);
        return 0;
    }

    @Override
    public List<TeamInfoBO> getTeamInfoByCourseId(Integer courseId) throws IdNotFoundException {
        List<TeamInfoBO> list = new LinkedList<>();
        List<CourseSelectionDO> courseSelections = courseSelectionRepository.findAllByCourse_CourseIdOrderByTeam(courseId);
        for (int i = 0; i < courseSelections.size() - 1; i++) {
            if (courseSelections.get(i).getTeam() == courseSelections.get(i + 1).getTeam() || courseSelections.get(i).getTeam() == null) {
                courseSelections.remove(i);
                i--;
            }
        }
        for (CourseSelectionDO cs : courseSelections) {
            list.add(getTeamInfo(cs.getTeam().getTeamId()));
        }
        return list;

    }
}
