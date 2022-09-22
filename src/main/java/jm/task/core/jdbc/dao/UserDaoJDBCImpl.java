package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connect = Util.getConnection();
             Statement state = connect.createStatement()) {

            state.executeUpdate("CREATE TABLE IF NOT EXISTS user(" +
                    "Id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(30)," +
                    "lastName VARCHAR(50)," +
                    "age INT)");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try (Connection connect = Util.getConnection();
             Statement state = connect.createStatement()) {

            state.executeUpdate("DROP TABLE IF EXISTS user");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connect = Util.getConnection();
             Statement state = connect.createStatement()) {

            state.executeUpdate("INSERT user(name,lastName,age) VALUES ('" + name + "','" + lastName + "'," + age + ")");
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } 

    public void removeUserById(long id) {
        try (Connection connect = Util.getConnection();
             Statement state = connect.createStatement()) {

            state.executeUpdate("DELETE FROM user WHERE Id = " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection conn = Util.getConnection()) {

            List<User> list = new ArrayList<>();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            while (resultSet.next()) {

                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int age = resultSet.getInt(4);
                User user = new User(name, lastName, (byte) age);
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Connection connect = Util.getConnection();
             Statement state = connect.createStatement()) {

            state.executeUpdate("DELETE from user");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
