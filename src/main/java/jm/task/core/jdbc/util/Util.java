package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String USER = "webscout";
    private static final String PASSWORD = "aq1";
    private static Connection con = null;

    private Util() {}

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            if (con != null && !con.isClosed()) {
                System.out.println("Connection established");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection()  {
        try {
            if (con != null) {
                con.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
