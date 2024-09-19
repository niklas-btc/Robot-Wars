package com.main.database;
import java.sql.*;

public class Driver {

    static String jdbcUrl = "jdbc:mysql://localhost:3306/testprojekt?useSSL=false&serverTimezone=UTC";
    static String username = "root";
    static String password = "";

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace(); // Hier könntest du auch eine Logging-Library wie Log4j verwenden
        }
        return con;
    }

    public ResultSet executeQuery(Connection con, String query) {
        if(query.isEmpty()) return null;
        ResultSet result = null;

        try {
            Statement stmt = con.createStatement();
            result = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
