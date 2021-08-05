package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Джо", "Байден", (byte) 78);
        userService.saveUser("Трамп", "Дональд", (byte) 74);
        userService.saveUser("Барак", "Обама", (byte) 59);
        userService.saveUser("Джордж", "Буш", (byte) 74);
        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
