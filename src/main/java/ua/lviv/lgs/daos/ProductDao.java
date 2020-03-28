package ua.lviv.lgs.daos;

import org.apache.log4j.Logger;
import ua.lviv.lgs.ConnectionUtil;
import ua.lviv.lgs.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDao implements CRUD<Product> {
    private static Logger LOG = Logger.getLogger(ProductDao.class);

    private Connection connection;
    private static final String SELECT_BY_ID = "SELECT * FROM products WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM products";
    private static final String UPDATE =
            "UPDATE products SET name=?, description=?, price=? WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM products WHERE id=?";
    private static final String INSERT =
            "INSERT INTO products(name, description, price) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_IN = "SELECT * FROM products WHERE id IN";

    public ProductDao() {
        this.connection = ConnectionUtil.getConnection();
    }

    @Override
    public Optional<Product> getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return Optional.ofNullable(Product.of(resultSet));
        } catch (SQLException e) {
            String error = String.format("Can't find product with id = %d", id);
            LOG.error(error, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);


            while(resultSet.next()){
                products.add(Product.of(resultSet));
            }

            return products;
        } catch (SQLException e) {
            String error = "Can't find products";
            LOG.error(error, e);
        }

        return products;
    }

    @Override
    public void update(int id, Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String error = String.format("Can't update product with id = %d", id);
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
            String error = String.format("Can't delete product with id = %d", id);
            LOG.error(error, e);
        }
    }

    @Override
    public int insert(Product product) {
        String message = String.format("Will create new product with name = %s", product.getName());
        LOG.debug(message);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            generatedKeys.next();

            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            String error = String.format("Can't insert product with name = %s", product.getName());
            LOG.error(error, e);
        }

        return 0;
    }

    public List<Product> getByIds(Set<Integer> productIds) {
        List<Product> products = new ArrayList<>();

        try {
            String ids = productIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));

            String query = String.format("%s (%s)", SELECT_ALL_IN, ids);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                products.add(Product.of(resultSet));
            }
        } catch (SQLException e) {
            String error = "Can't get products";
            LOG.error(error, e);
        }

        return products;
    }
}
