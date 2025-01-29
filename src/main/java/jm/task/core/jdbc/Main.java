package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Johny", "Silverhand", (byte) 34);
        userService.saveUser("Spider", "Murphy", (byte) 28);
        userService.saveUser("Morgan", "Blackhand", (byte) 37);
        userService.saveUser("Adam", "Smasher", (byte) 41);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
