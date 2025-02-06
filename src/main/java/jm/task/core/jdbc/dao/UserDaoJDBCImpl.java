package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl  implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable()  {
        String sql = "CREATE TABLE IF NOT EXISTS users  (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";

        try (Connection con = Util.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable()  {
        String sql = "DROP TABLE IF EXISTS users;";

        try (Connection con = Util.getConnection();
             Statement stmt = con.createStatement()){
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age)  {
        String sql = "INSERT INTO  users (name, lastName, age) VALUES (?,?,?)";

        try (Connection con = Util.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false);

            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);

            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                if (Util.getConnection() != null) {
                    Util.getConnection().rollback();
                    System.out.println("Transaction rolled back");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id)  {
        String sql = "DELETE  FROM users WHERE id = ?";

        try (Connection con = Util.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false);

            ps.setLong(1, id);
            System.out.println("Preparing to delete user with ID: " + id);

            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                if (Util.getConnection() != null) {
                    Util.getConnection().rollback();
                    System.out.println("Transaction rolled back");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers()  {
        List<User> users = new ArrayList<User>();

        String sql = "SELECT * FROM users";

        try (Connection con = Util.getConnection();
             Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void cleanUsersTable()  {
        String sql = "TRUNCATE  TABLE users;";

        try (Connection con = Util.getConnection();
             Statement stmt = con.createStatement()){
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
