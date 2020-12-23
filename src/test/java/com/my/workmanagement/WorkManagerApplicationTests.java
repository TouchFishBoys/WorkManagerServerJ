package com.my.workmanagement;

import com.my.workmanagement.model.ERole;
import com.my.workmanagement.model.User;
import com.my.workmanagement.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorkManagerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testJwt() {
        User user = new User("test_user", "test_pass", ERole.STUDENT);
        String generatedToken = JwtUtils.generateToken(user);
        System.out.println("Generated token:" + generatedToken);

        System.out.println(JwtUtils.getUsername(generatedToken));
    }
}
