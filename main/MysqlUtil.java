package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlUtil {
    private static volatile MysqlUtil mysqlUtil;
    private static String URL="jdbc:mysql://127.0.0.1:3306/test?serverTimezone=GMT&useUnicode=true&characerEncoding=utf-8&useSSL=true";
    private static String mysqlUsername = "root";
    private static String mysqlPassword = "ljj5588006";

    private MysqlUtil() {

    }

    public static MysqlUtil getInstance() {
        if (mysqlUtil == null) {
            synchronized (MysqlUtil.class) {
                if (mysqlUtil == null) {
                    mysqlUtil = new MysqlUtil();
                }
            }
        }

        return mysqlUtil;
    }

    public boolean login(String username, String password) {
        Connection connection = null;
        Statement statement   = null;
        try {
            connection = DriverManager.getConnection(URL, mysqlUsername, mysqlPassword);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM user WHERE username = '" + username + "' AND password = '" + password + "'");
            if (!resultSet.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e1) {

            }
        }

        return true;
    }



    public boolean register(String username, String password) {
        Connection connection = null;
        Statement statement   = null;
        try {
            connection = DriverManager.getConnection(URL, mysqlUsername, mysqlPassword);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM user WHERE username = '" + username + "'");
            if (resultSet.next()) {
                return false;
            }
            int mark = statement.executeUpdate("INSERT INTO user (username, password) VALUES( '" +  username + "', '"  + password  + "')");
            if (mark == 0) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e1) {

            }
        }

        return true;
    }
}
