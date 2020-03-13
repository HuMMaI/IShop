package ua.lviv.lgs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/ishop";
    private static String USERNAME = "root";
    private static String PASSWORD = "Qwerty156qqqq";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't connection to db");
        }
    }
}
