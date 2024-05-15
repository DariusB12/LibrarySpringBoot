package org.example.libraryproject.utils;

import java.io.IOException;
import java.util.Properties;

public class PasswordManager {
    private static String passwordEmployee = null;
    private PasswordManager(){}

    public static String getEmployeePassword() throws IOException {
        if(passwordEmployee ==null){
            Properties properties = new Properties();
            properties.load(PasswordManager.class.getResourceAsStream("/app.properties"));
            passwordEmployee = properties.getProperty("password-employee");
        }
        return passwordEmployee;
    }
}
