package ua.lviv.lgs.daos;

import org.apache.log4j.Logger;
import ua.lviv.lgs.ConnectionUtil;
import ua.lviv.lgs.entities.Bucket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BucketDao implements CRUD<Bucket>{
    private static Logger LOG = Logger.getLogger(BucketDao.class);

    private Connection connection;
    private static final String SELECT_BY_ID = "SELECT * FROM bucket WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM bucket";
    private static final String UPDATE =
            "UPDATE bucket SET user_id=?, product_id=?, purchase_date=? WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM bucket WHERE id=?";
    private static final String INSERT =
            "INSERT INTO bucket(user_id, product_id, purchase_date) VALUES (?, ?, ?)";
    private static final String DELETE_BY_PRODUCT_ID = "DELETE FROM bucket WHERE product_id=?";

    public BucketDao() {
        connection = ConnectionUtil.getConnection();
    }

    @Override
    public Optional<Bucket> getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return Optional.ofNullable(Bucket.of(resultSet));
        } catch (SQLException e) {
            String error = String.format("Can't find bucket with id = %d", id);
            LOG.error(error, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Bucket> getAll() {
        List<Bucket> buckets = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while(resultSet.next()){
                buckets.add(Bucket.of(resultSet));
            }

            return buckets;
        } catch (SQLException e) {
            String error = "Can't find buckets";
            LOG.error(error, e);
        }

        return buckets;
    }

    @Override
    public void update(int id, Bucket bucket) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setInt(1, bucket.getUserId());
            preparedStatement.setInt(2, bucket.getProductId());
            preparedStatement.setTimestamp(3, new Timestamp(bucket.getPurchaseDate().getTime()));
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String error = String.format("Can't update bucket with id = %d", id);
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
            String error = String.format("Can't delete bucket with id = %d", id);
            LOG.error(error, e);
        }
    }

    @Override
    public int insert(Bucket bucket) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, bucket.getUserId());
            preparedStatement.setInt(2, bucket.getProductId());
            preparedStatement.setTimestamp(3, new Timestamp(bucket.getPurchaseDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            generatedKeys.next();

            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            String error = String.format("Can't create bucket with user id = %d and product id = %d",
                    bucket.getUserId(), bucket.getProductId());
            LOG.error(error, e);
        }

        return 0;
    }

    public void deleteByProductId(int productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_PRODUCT_ID);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String error = String.format("Can't delete buckets with id = %d", productId);
            LOG.error(error, e);
        }
    }
}
