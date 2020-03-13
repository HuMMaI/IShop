package ua.lviv.lgs.daos;

import ua.lviv.lgs.ConnectionUtil;
import ua.lviv.lgs.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements CRUD<User> {
    private Connection connection;
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String UPDATE =
            "UPDATE users SET email=?, first_name=?, last_name=?, role=? WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id=?";
    private static final String INSERT =
            "INSERT INTO users(email, first_name, last_name, role) VALUES (?, ?, ?, ?)";

    public UserDao() {
        connection = ConnectionUtil.getConnection();
    }


    @Override
    public User getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return User.of(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find user");
        }
    }

    @Override
    public List<User> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<User> users = new ArrayList<>();

            while(resultSet.next()){
                users.add(User.of(resultSet));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find users");
        }
    }

    @Override
    public void update(int id, User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't update user");
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't delete user");
        }
    }

    @Override
    public int insert(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            generatedKeys.next();

            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't insert user");
        }
    }
}