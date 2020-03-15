package ua.lviv.lgs.daos;

import org.apache.log4j.Logger;
import ua.lviv.lgs.ConnectionUtil;
import ua.lviv.lgs.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class UserDao implements CRUD<User> {
    private static Logger LOG = Logger.getLogger(UserDao.class);

    private Connection connection;
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String UPDATE =
            "UPDATE users SET email=?, first_name=?, last_name=?, role=?, password=? WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id=?";
    private static final String INSERT =
            "INSERT INTO users(email, first_name, last_name, role, password) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM users WHERE email=?";

    public UserDao() {
        connection = ConnectionUtil.getConnection();
    }


    @Override
    public Optional<User> getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return Optional.ofNullable(User.of(resultSet));
        } catch (SQLException e) {
            String error = String.format("Can't find user with id = %d", id);
            LOG.error(error, e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<User>> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                users.add(User.of(resultSet));
            }

            Optional<List<User>> optionalUsers = Optional.ofNullable(users);

            return optionalUsers;
        } catch (SQLException e) {
            String error = "Can't find users";
            LOG.error(error, e);
        }

        return Optional.empty();
    }

    @Override
    public void update(int id, User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String error = String.format("Can't update user with id = %d", id);
            LOG.error(error, e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String error = String.format("Can't delete user with id = %d", id);
            LOG.error(error, e);
        }
    }

    @Override
    public int insert(User user) {
        String message = String.format("Will create new user with email = %s", user.getEmail());
        LOG.debug(message);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            generatedKeys.next();

            message = String.format("Created a new user with email = %s and id = %d",
                    user.getEmail(), generatedKeys.getInt(1));
            LOG.info(message);

            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            String error = String.format("Can't create user with email = %s", user.getEmail());
            LOG.error(error, e);
        }

        return 0;
    }

    public Optional<User> getByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL);

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return Optional.of(User.of(resultSet));
            }
        } catch (SQLException e) {
            String error = String.format("Can'r find user with email = %s", email);
            LOG.error(error, e);
        }

        return Optional.empty();
    }
}
