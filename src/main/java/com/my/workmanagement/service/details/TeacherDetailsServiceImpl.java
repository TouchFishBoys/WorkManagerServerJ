package com.my.workmanagement.service.details;

import com.my.workmanagement.entity.TeacherDO;
import com.my.workmanagement.model.ERole;
import com.my.workmanagement.model.WMUserDetails;
import com.my.workmanagement.repository.TeacherRepository;
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

@Service("teacherDetailsService")
public class TeacherDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(TeacherDetailsServiceImpl.class);
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherDetailsServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    /**
     * 根据工号获取TeacherDetails
     *
     * @param teacherNumber 教师工号
     * @return UserDetails
     * @throws UsernameNotFoundException 找不到工号对应的教师
     */
    @Override
    public UserDetails loadUserByUsername(String teacherNumber) throws UsernameNotFoundException {
        TeacherDO teacher = teacherRepository.findByTeacherNumber(teacherNumber);
        String[] roles = {ERole.ROLE_TEACHER.name()};
        if (teacher == null) {
            logger.info("Teacher {} not found", teacherNumber);
            throw new UsernameNotFoundException(String.format("No user found with teacher_id '%s'", teacherNumber));
        } else {
            logger.info("Found teacher {}", teacher.getTeacherName());
            return new WMUserDetails(
                    teacher.getTeacherNum(),
                    teacher.getTeacherPassword(),
                    ERole.ROLE_TEACHER,
                    Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
        }
    }
}
