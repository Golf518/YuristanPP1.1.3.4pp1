package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = null;
    private static final String createUsersTableSql = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastname VARCHAR(255), age INT)";
    private static final String dropUsersTableSql = "DROP TABLE IF EXISTS users";
    private static final String saveUserSql = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
    private static final String removeUserByIdSql = "DELETE FROM users WHERE  ? ";
    private static final String getAllUsersSql = "SELECT * FROM users";
    private static final String cleanUsersTableSql = "TRUNCATE TABLE users";
    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        try (
             Statement stateCreation = connection.createStatement()) {
            stateCreation.executeUpdate(createUsersTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (
             Statement stateDrop = connection.createStatement()) {
            stateDrop.executeUpdate(dropUsersTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (
             PreparedStatement stateSaving = connection.prepareStatement(saveUserSql)) {

            stateSaving.setString(1, name);
            stateSaving.setString(2, lastName);
            stateSaving.setByte(3, age);
            stateSaving.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (
             PreparedStatement sateRemovement = connection.prepareStatement(removeUserByIdSql)) {
            sateRemovement.setLong(1, id);
            sateRemovement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (
             ResultSet resultSet = connection.createStatement().executeQuery(getAllUsersSql)) {
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    public void cleanUsersTable() {
        try (
             Statement stateClean = connection.createStatement()) {
            stateClean.executeUpdate(cleanUsersTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}