package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Johny", "Silverhand", (byte) 34);
        System.out.println("User с именем — " + userService.getAllUsers().get(userService.getAllUsers().size() - 1).getName() + " добавлен в базу данных");


        userService.saveUser("Spider", "Murphy", (byte) 28);
        System.out.println("User с именем — " + userService.getAllUsers().get(userService.getAllUsers().size() - 1).getName() + " добавлен в базу данных");

        userService.saveUser("Morgan", "Blackhand", (byte) 37);
        System.out.println("User с именем — " + userService.getAllUsers().get(userService.getAllUsers().size() - 1).getName() + " добавлен в базу данных");

        userService.saveUser("Adam", "Smasher", (byte) 41);
        System.out.println("User с именем — " + userService.getAllUsers().get(userService.getAllUsers().size() - 1).getName() + " добавлен в базу данных");


        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeConnection();
    }
}
