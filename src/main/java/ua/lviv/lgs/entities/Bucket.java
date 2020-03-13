package ua.lviv.lgs.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Bucket {
    private int id;
    private int userId;
    private int productId;
    private LocalDateTime purchaseDate;

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

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
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
