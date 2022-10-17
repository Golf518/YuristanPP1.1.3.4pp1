package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao myFirstDao = new UserDaoJDBCImpl();
    public void createUsersTable() {
        myFirstDao.createUsersTable();
    }

    public void dropUsersTable() {
        myFirstDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        myFirstDao.saveUser(name, lastName, age);
        System.out.println("User – " + name + " " + lastName + " добавлен в БД");
    }

    public void removeUserById(long id) {
        myFirstDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> users =  myFirstDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }
    public void cleanUsersTable() {
        myFirstDao.cleanUsersTable();
    }
}