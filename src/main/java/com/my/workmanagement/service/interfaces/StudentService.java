package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.UnsupportedFileTypeException;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.payload.response.student.CourseListResponse;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface StudentService {
    Resource getStudentExcel(String courseId);

    boolean importStudents(Integer courseId, MultipartFile file, String defaultPassword) throws UnsupportedFileTypeException, IOException, IdNotFoundException;

    StudentInfoBO getStudentInfo(Integer studentId) throws IdNotFoundException;

    List<CourseInfoBO> getSelectedCourseInfo(Integer studentId) throws IdNotFoundException;

    List<StudentInfoBO> getStudentsByTeamId(Integer teamId) throws IdNotFoundException;

    CourseListResponse getHostedCourseInfoList(Integer studentId, Pageable page) throws IdNotFoundException;
}
