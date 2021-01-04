package com.my.workmanagement.service;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.FinalWorkDO;
import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.entity.TeamDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.WordTemplateNotFoundException;
import com.my.workmanagement.model.bo.FinalWorkBO;
import com.my.workmanagement.model.bo.QaTableBO;
import com.my.workmanagement.repository.CourseRepository;
import com.my.workmanagement.repository.FinalWorkRepository;
import com.my.workmanagement.repository.StudentRepository;
import com.my.workmanagement.repository.TeamRepository;
import com.my.workmanagement.service.interfaces.FileStorageService;
import com.my.workmanagement.service.interfaces.FinalWorkService;
import com.my.workmanagement.service.interfaces.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class FinalWorkServiceImpl implements FinalWorkService {
    private static final Logger logger = LoggerFactory.getLogger(FinalWorkServiceImpl.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    @Value("${workmanager.storage.qa-table-template-location}")
    private static String TEMPLATE_LOCATION;
    private static final File templateFile = new File(TEMPLATE_LOCATION);
    private final TeamService teamService;
    private final FinalWorkRepository finalWorkRepository;
    private final TeamRepository teamRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final FileStorageService fileStorageService;

    FinalWorkServiceImpl(
            FinalWorkRepository finalWorkRepository,
            TeamRepository teamRepository,
            TeamService teamService,
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            FileStorageService fileStorageService
    ) {
        this.finalWorkRepository = finalWorkRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public FinalWorkBO getFinalWorkInfo(Integer teamId) throws IdNotFoundException {
        TeamDO team = teamRepository.findByTeamId(teamId);
        if (team == null) {
            // 没找到队伍
            throw new IdNotFoundException("team");
        }
        FinalWorkDO finalWork = team.getFinalWork();
        if (finalWork == null) {
            throw new IdNotFoundException("finalWork");
        }
        return FinalWorkBO.FinalWorkBOBuilder.aFinalWorkBO()
                .withFinalWorkId(finalWork.getFworkId())
                .withFinalWorkName(finalWork.getFworkName())
                .withTeamName(team.getTeamName())
                .withSubmitTime(finalWork.getTimeUpload())
                .withDescription(finalWork.getFworkDescription())
                .withAuthors(teamService.getTeamMembersName(teamId))
                .withScore(finalWork.getFworkScore())
                .withDocumentScore(finalWork.getDocumentScore())
                .build();
    }

    @Override
    @Transactional
    public boolean setFinalWorkScore(Integer finalWork, Integer score) throws IdNotFoundException {
        if (!finalWorkRepository.existsById(finalWork)) {
            throw new IdNotFoundException("finalWorkId");
        }
        return finalWorkRepository.setScoreByFinalWorkId(finalWork, score) > 0;
    }

    @Override
    @Transactional
    public boolean setDocumentScore(Integer finalWork, Integer score) throws IdNotFoundException {
        if (!finalWorkRepository.existsById(finalWork)) {
            throw new IdNotFoundException("finalWorkId");
        }
        return finalWorkRepository.setDocumentScoreByFinalWorkId(finalWork, score) > 0;
    }

    @Override
    public Resource loadDocumentByFworkId(Integer finalWorkId) throws FileNotFoundException {
        // TODO: 2021/1/4  
        return null;
    }

    @Override
    public Resource loadFworkFileByFworkId(Integer finalWorkId) throws FileNotFoundException {
        // TODO: 2021/1/4  
        return null;
    }

    @Override
    public boolean generateQaTable(List<QaTableBO.QaTableItemBO> items, Integer courseId, Integer studentId, String location, Integer score)
            throws WordTemplateNotFoundException, IOException, IdNotFoundException {
        // TODO: 2021/1/4
        StudentDO student = studentRepository.findByStudentId(studentId);
        CourseDO course = courseRepository.findByCourseId(courseId);
        if (student == null) {
            throw new IdNotFoundException("student id");
        }
        if (course == null) {
            throw new IdNotFoundException("course id");
        }
        Date date = new Date();
        XWPFTemplate template = XWPFTemplate.compile(templateFile()).render(
                new HashMap<String, Object>() {
                    {
                        put("items", items);
                        put("className", student.getStudentClass());
                        put("studentNum", student.getStudentNum());
                        put("studentName", student.getStudentName());
                        put("qaTime", sdf.format(date));
                    }
                }
        );
        template.write(new FileOutputStream("output.docx"));
        return true;
    }

    @Bean
    private File templateFile() throws WordTemplateNotFoundException {
        if (!templateFile.exists()) {
            throw new WordTemplateNotFoundException();
        }
        return templateFile;
    }

    @Bean
    private Configure config() {
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        return Configure.newBuilder()
                .bind("items", policy).build();
    }
}
