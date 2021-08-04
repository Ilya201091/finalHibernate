package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/INTtech?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Bkmz201091";
    private Connection connection;

    public Util(){
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




    // реализуйте настройку соеденения с БД
}
