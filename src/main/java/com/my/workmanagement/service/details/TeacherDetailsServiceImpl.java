package com.my.workmanagement.service.details;

import com.my.workmanagement.entity.TeacherDO;
import com.my.workmanagement.model.ERole;
import com.my.workmanagement.model.WMUserDetails;
import com.my.workmanagement.repository.TeacherRepository;
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
            throw new UsernameNotFoundException(String.format("No user found with teacher_id '%s'", teacherNumber));
        } else {
            return new WMUserDetails(
                    teacher.getTeacherNumber(),
                    teacher.getSecretKey(),
                    ERole.ROLE_TEACHER,
                    Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
        }
    }
}
