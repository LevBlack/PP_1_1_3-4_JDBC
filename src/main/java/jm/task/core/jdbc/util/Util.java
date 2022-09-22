package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost/people";
        String username = "root";
        String password = "root";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to People DB succesfull!");
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
