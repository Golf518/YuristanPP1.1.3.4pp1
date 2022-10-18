package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Cristiano", "Ronaldo", (byte) 46);
        userService.saveUser("Franck", "Lampart", (byte) 34);
        userService.saveUser("Lionel", "Messi", (byte) 37);
        userService.saveUser("David", "Beckham", (byte) 50);

        userService.removeUserById(4);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}