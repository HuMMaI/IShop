package ua.lviv.lgs.daos;

import ua.lviv.lgs.ConnectionUtil;
import ua.lviv.lgs.entities.Bucket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketDao implements CRUD<Bucket>{
    private Connection connection;
    private static final String SELECT_BY_ID = "SELECT * FROM bucket WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM bucket";
    private static final String UPDATE =
            "UPDATE bucket SET user_id=?, product_id=?, purchase_date=? WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM bucket WHERE id=?";
    private static final String INSERT =
            "INSERT INTO bucket(user_id, product_id, purchase_date) VALUES (?, ?, ?)";

    public BucketDao() {
        connection = ConnectionUtil.getConnection();
    }

    @Override
    public Bucket getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return Bucket.of(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find bucket");
        }
    }

    @Override
    public List<Bucket> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<Bucket> buckets = new ArrayList<>();

            while(resultSet.next()){
                buckets.add(Bucket.of(resultSet));
            }

            return buckets;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find buckets");
        }
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
            e.printStackTrace();
            throw new RuntimeException("Can't update bucket");
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
            throw new RuntimeException("Can't delete bucket");
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
            e.printStackTrace();
            throw new RuntimeException("Can't insert bucket");
        }
    }
}
