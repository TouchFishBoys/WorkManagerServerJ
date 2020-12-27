package com.my.workmanagement.service.details;

import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.model.ERole;
import com.my.workmanagement.model.WMUserDetails;
import com.my.workmanagement.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Student 的 UserDetailsService
 *
 * @author Kerit
 */
@Service("studentDetailsService")
public class StudentDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(StudentDetailsServiceImpl.class);
    StudentRepository studentRepository;

    @Autowired
    public StudentDetailsServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * 根据学号获取 StudentDetails
     *
     * @param studentNumber 学号
     * @return UserDetails
     * @throws UsernameNotFoundException 用户不存在
     */
    @Override
    public UserDetails loadUserByUsername(String studentNumber) throws UsernameNotFoundException {
        StudentDO student = studentRepository.findByStudentNum(studentNumber);
        String[] roles = {ERole.STUDENT.name()};
        if (student == null) {
            logger.info("Student {} not found", studentNumber);
            throw new UsernameNotFoundException(String.format("No user found with student_id '%s", studentNumber));
        } else {
            logger.info("Found student: {}", student.getStudentName());
            return new WMUserDetails(
                    student.getStudentId(),
                    student.getStudentNum(),
                    student.getStudentPassword(),
                    ERole.STUDENT,
                    Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
        }
    }
}
