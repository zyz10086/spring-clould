package com.spring.elasticjob.anno;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用注解：
 *
 * */
@RestController
public class PasswordUtils {

    @GetMapping("/validatePassword")
    @UseCases(id="47",description="Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }


    @GetMapping("/encryptPassword")
    @UseCases(id ="48")
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }
}
