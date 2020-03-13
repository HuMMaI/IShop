package ua.lviv.lgs.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class Bucket {
    private int id;
    private int userId;
    private int productId;
    private Date purchaseDate;

    public Bucket(int id, int userId, int productId, Date purchaseDate) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
    }

    public Bucket(int userId, int productId, Date purchaseDate) {
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
    }

    public static Bucket of(ResultSet resultSet){
        try {
            int id = resultSet.getInt("id");
            int userId = resultSet.getInt("user_id");
            int productId = resultSet.getInt("product_id");
            Date purchaseDate = resultSet.getDate("purchase_date");

            return new Bucket(id, userId, productId, purchaseDate);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't create bucket");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return id == bucket.id &&
                userId == bucket.userId &&
                productId == bucket.productId &&
                Objects.equals(purchaseDate, bucket.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, purchaseDate);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
