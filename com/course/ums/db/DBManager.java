package com.course.ums.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String DB_URL = "jdbc:mysql://learner:student@localhost/university_management_app?useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}

