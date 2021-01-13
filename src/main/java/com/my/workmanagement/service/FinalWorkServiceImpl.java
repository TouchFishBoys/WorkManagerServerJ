package com.my.workmanagement.service;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.my.workmanagement.config.StorageConfiguration;
import com.my.workmanagement.entity.*;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.exception.WordTemplateNotFoundException;
import com.my.workmanagement.model.bo.FinalWorkBO;
import com.my.workmanagement.model.bo.QaTableBO;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.repository.*;
import com.my.workmanagement.service.interfaces.FileStorageService;
import com.my.workmanagement.service.interfaces.FinalWorkService;
import com.my.workmanagement.service.interfaces.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class FinalWorkServiceImpl implements FinalWorkService {
    private static final Logger logger = LoggerFactory.getLogger(FinalWorkServiceImpl.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private static final String WORD_CONTENT_TYPE_VALUE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    private final TeamService teamService;
    private final FinalWorkRepository finalWorkRepository;
    private final TeamRepository teamRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final FileStorageService fileStorageService;
    private final CourseSelectionRepository courseSelectionRepository;
    @javax.annotation.Resource
    private StorageConfiguration storageConfiguration;
    private File templateFile;

    FinalWorkServiceImpl(
            FinalWorkRepository finalWorkRepository,
            TeamRepository teamRepository,
            TeamService teamService,
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            FileStorageService fileStorageService,
            CourseSelectionRepository courseSelectionRepository
    ) {
        this.finalWorkRepository = finalWorkRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.fileStorageService = fileStorageService;
        this.courseSelectionRepository = courseSelectionRepository;
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
    public Resource loadDocumentByFworkId(Integer finalWorkId) throws StorageFileNotFoundException {
        return loadSomethingByFworkId(finalWorkId, "document.docx");
    }

    @Override
    public Resource loadFworkFileByFworkId(Integer finalWorkId) throws StorageFileNotFoundException {
        return loadSomethingByFworkId(finalWorkId, "file.war");
    }

    private Resource loadSomethingByFworkId(Integer finalWorkId, String filename) throws StorageFileNotFoundException {
        FinalWorkDO fwork = finalWorkRepository.getByFworkId(finalWorkId);
        TeamDO team = teamRepository.getByFinalWork_FworkId(finalWorkId);
        CourseDO course = courseSelectionRepository.getFirstByTeam(team).getCourse();
        logger.debug("下载大作业：Course: {}, Team: {}, Fwork: {}", course.getCourseId(), team.getTeamId(), finalWorkId);
        String fileLocation = course.getCourseId() + "/final/" + team.getTeamId() + "/" + filename;
        logger.debug("下载大作业：Location: {}", fileLocation);
        return fileStorageService.loadAsResource(fileLocation);
    }

    @Override
    public boolean generateQaTable(List<QaTableBO.QaTableItemBO> items, Integer courseId, Integer studentId, String location, Integer score)
            throws WordTemplateNotFoundException, IOException, IdNotFoundException {
        StudentDO student = studentRepository.findByStudentId(studentId);
        CourseDO course = courseRepository.findByCourseId(courseId);
        if (student == null) {
            throw new IdNotFoundException("student id");
        }
        if (course == null) {
            throw new IdNotFoundException("course id");
        }
        CourseSelectionDO courseSelection = courseSelectionRepository.findFirstByStudent_StudentIdAndCourse_CourseId(studentId, courseId);
        Date date = new Date();
        XWPFTemplate template = XWPFTemplate.compile(templateFile()).render(
                new HashMap<String, Object>() {
                    {
                        put("items", items);
                        put("className", student.getStudentClass());
                        put("studentNum", student.getStudentNum());
                        put("studentName", student.getStudentName());
                        put("qaTime", sdf.format(date));
                        put("qaLocation", location);
                        put("qaScore", score);
                    }
                }
        );
        if (courseSelection.getTeam() == null) {
            throw new IdNotFoundException("team id");
        }
        String outputPath = courseId + "/final/" + courseSelection.getTeam().getTeamId() + "/qa-table";
        String fileName = student.getStudentNum() + ".docx";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        template.write(outputStream);
        fileStorageService.store(outputStream.toByteArray(), outputPath, fileName, WORD_CONTENT_TYPE_VALUE);
        outputStream.close();
        return true;
    }

    @Override
    @Transactional
    public void uploadFinalWork(Integer courseId, Integer studentId, MultipartFile file)
            throws IdNotFoundException, IOException {
        CourseSelectionDO courseSelection = courseSelectionRepository.findFirstByStudent_StudentIdAndCourse_CourseId(studentId, courseId);

        if (courseSelection == null || courseSelection.getTeam() == null) {
            throw new IdNotFoundException("team");
        }
        TeamDO team = courseSelection.getTeam();
        Integer teamId = courseSelection.getTeam().getTeamId();

        FinalWorkDO finalWork = new FinalWorkDO();
        finalWork.setTimeUpload(new Timestamp(new Date().getTime()));
        finalWork.setFworkName(UUID.randomUUID().toString());
        FinalWorkDO newFinalWork = finalWorkRepository.save(finalWork);
        team.setFinalWork(newFinalWork);
        teamRepository.save(team);

        String location = courseId + "/final/" + teamId;

        fileStorageService.store(file, location, "file.war");
    }

    @Override
    public void uploadDocument(Integer courseId, Integer studentId, MultipartFile file)
            throws IdNotFoundException, IOException {
        CourseSelectionDO courseSelection = courseSelectionRepository.findFirstByStudent_StudentIdAndCourse_CourseId(studentId, courseId);

        if (courseSelection == null || courseSelection.getTeam() == null) {
            throw new IdNotFoundException("team");
        }
        Integer teamId = courseSelection.getTeam().getTeamId();

        String location = courseId + "/final/" + teamId;

        fileStorageService.store(file, location, "document.docx");
    }

    @Override
    public List<StudentInfoBO> getAuthors(Integer finalId) throws IdNotFoundException {
        TeamDO team = teamRepository.getByFinalWork_FworkId(finalId);
        if (team == null) {
            throw new IdNotFoundException("team");
        }
        return teamService.getTeamMembers(team.getTeamId());
    }

    private File templateFile() throws WordTemplateNotFoundException {
        if (templateFile == null) {
            logger.info("Template location is {}", storageConfiguration.getQaTableTemplateLocation());
            this.templateFile = new File(storageConfiguration.getQaTableTemplateLocation());
        }
        if (!templateFile.exists()) {
            throw new WordTemplateNotFoundException();
        }
        return templateFile;
    }

    @Override
    public void setQAScore(Integer courseId, Integer studentId, Integer qaScore) {
        courseSelectionRepository.setQAScoreByStudentIdAndCourseId(qaScore, studentId, courseId);
    }


    @Bean
    private Configure config() {
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        return Configure.newBuilder()
                .bind("items", policy).build();
    }
}
