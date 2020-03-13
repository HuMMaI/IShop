package ua.lviv.lgs.daos;

import ua.lviv.lgs.ConnectionUtil;
import ua.lviv.lgs.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements CRUD<Product> {
    private Connection connection;
    private static final String SELECT_BY_ID = "SELECT * FROM products WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM products";
    private static final String UPDATE =
            "UPDATE products SET name=?, description=?, price=? WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM products WHERE id=?";
    private static final String INSERT =
            "INSERT INTO products(name, description, price) VALUES (?, ?, ?)";

    public ProductDao() {
        this.connection = ConnectionUtil.getConnection();
    }

    @Override
    public Product getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return Product.of(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find product");
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<Product> products = new ArrayList<>();

            while(resultSet.next()){
                products.add(Product.of(resultSet));
            }

            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find products");
        }
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
            e.printStackTrace();
            throw new RuntimeException("Can't update product");
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
            throw new RuntimeException("Can't delete product");
        }
    }

    @Override
    public int insert(Product product) {
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
            e.printStackTrace();
            throw new RuntimeException("Can't insert product");
        }
    }
}
