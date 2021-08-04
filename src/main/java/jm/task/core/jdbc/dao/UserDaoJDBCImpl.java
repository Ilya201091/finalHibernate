package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private final String DELETE = "DELETE FROM User WHERE id = ?";
    private final String GETALL = "SELECT * FROM User";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try  {
            statement.executeUpdate("INSERT INTO User " +
                    "(id INTEGER not NULL," +
                    "name VARCHAR(50)," +
                    "lastName VARCHAR(50)," +
                    "age INTEGER not NULL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM User WHERE NAME");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,lastName);
            preparedStatement.setInt(4,age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            List<User> users = new ArrayList<>();
            while (resultSet.next()){
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM User");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
