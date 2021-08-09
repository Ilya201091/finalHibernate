package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    private Connection connection = Util.start();
    private PreparedStatement preparedStatement;



    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement())  {
            private final String TABLE = "CREATE TABLE users " +
                    "(id INT not NULL PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "lastName VARCHAR(50)," +
                    "age INT not NULL)";
            statement.executeUpdate(TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM User WHERE NAME");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, lastname, age) VALUES(?,?,?)")) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void removeUserById(long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id = ?")) {
            preparedStatement.setLong(1,id);
            System.out.println("Удален пользователь под индексом" + id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT id, name, lastName, age from users";
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String del = "DELETE FROM User";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(del);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
