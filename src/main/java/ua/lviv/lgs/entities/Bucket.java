package ua.lviv.lgs.entities;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bucket")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "purchase_date")
    private Date purchaseDate;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private int id;
        private int userId;
        private int productId;
        private Date purchaseDate;

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setUserId(int userId){
            this.userId = userId;
            return this;
        }

        public Builder setProductId(int productId){
            this.productId = productId;
            return this;
        }

        public Builder setPurchaseDate(Date purchaseDate){
            this.purchaseDate = purchaseDate;
            return this;
        }

        public Bucket build(){
            Bucket bucket = new Bucket();
            bucket.setId(id);
            bucket.setUserId(userId);
            bucket.setProductId(productId);
            bucket.setPurchaseDate(purchaseDate);

            return bucket;
        }
    }

    public static Bucket of(ResultSet resultSet){
        try {
            int id = resultSet.getInt("id");
            int userId = resultSet.getInt("user_id");
            int productId = resultSet.getInt("product_id");
            Date purchaseDate = resultSet.getDate("purchase_date");

            return Bucket.builder()
                    .setId(id)
                    .setUserId(userId)
                    .setProductId(productId)
                    .setPurchaseDate(purchaseDate)
                    .build();
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
