package pl.coderslab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    static final String DB_URL_WORKSHOP = "jdbc:mysql://localhost:3306/workshop2?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    static final String DB_USER = "root";
    static final String DB_PASS = "coderslab";

    public static Connection connectWorkshop2() throws SQLException {
        return DriverManager.getConnection(DB_URL_WORKSHOP,DB_USER,DB_PASS);
    }

}
