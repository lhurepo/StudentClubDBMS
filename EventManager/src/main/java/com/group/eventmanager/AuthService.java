package com.group.eventmanager;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class AuthService {

    public Connection authenticate(String username, String password) throws Exception {
        String url = "jdbc:oracle:thin:@localhost:1522:stu";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
