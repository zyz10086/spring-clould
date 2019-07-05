package com.spring.clouldsentinel.inter;

public class PasswordUtils {
    @TestInterface(id="47",description="Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @TestInterface(id ="48")
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

}
