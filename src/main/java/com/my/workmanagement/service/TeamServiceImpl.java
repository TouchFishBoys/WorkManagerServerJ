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
import com.my.workmanagement.service.interfaces.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
    private final TeamRepository teamRepository;
    private final CourseSelectionRepository courseSelectionRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository,
                           CourseSelectionRepository courseSelectionRepository,
                           StudentRepository studentRepository
    ) {
        this.teamRepository = teamRepository;
        this.courseSelectionRepository = courseSelectionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Integer getTeamId(Integer studentId, Integer courseId) throws IdNotFoundException {
        CourseSelectionDO courseSelection = courseSelectionRepository.findFirstByStudent_StudentIdAndCourse_CourseId(studentId, courseId);
        return courseSelection.getTeam().getTeamId();
    }

    @Override
    public TeamInfoBO getTeamInfo(Integer teamId) throws IdNotFoundException {
        TeamDO team = teamRepository.findByTeamId(teamId);
        return getBoFromDo(team);
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

    public TeamInfoBO getBoFromDo(TeamDO team) throws IdNotFoundException {
        FinalWorkDO finalWork = team.getFinalWork();
        TeamInfoBO bo = new TeamInfoBO();
        bo.setTeamName(team.getTeamName());
        bo.setTeamId(team.getTeamId());
        bo.setMemberCount(getTeamMembers(team.getTeamId()).size());
        bo.setScore(finalWork.getFworkScore());
        bo.setDocumentScore(finalWork.getDocumentScore());
        return bo;
    }
}
