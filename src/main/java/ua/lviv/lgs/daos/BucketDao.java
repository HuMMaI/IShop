package ua.lviv.lgs.daos;

import org.apache.log4j.Logger;
import ua.lviv.lgs.ConnectionUtil;
import ua.lviv.lgs.EntityManagerUtils;
import ua.lviv.lgs.entities.Bucket;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class BucketDao implements CRUD<Bucket>{
    private static Logger LOG = Logger.getLogger(BucketDao.class);

    private static final String SELECT_ALL = "SELECT b FROM Bucket b";
    private static final String UPDATE =
            "UPDATE Bucket b SET b.userId = :userId, b.productId = :productId, b.purchaseDate = :purchaseDate WHERE b.id = :id";
    private static final String DELETE_BY_PRODUCT_ID = "DELETE FROM Bucket b WHERE b.productId = :productId";

    public BucketDao() {
    }

    @Override
    public Optional<Bucket> getById(int id) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();
        return Optional.ofNullable(entityManager.find(Bucket.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Bucket> getAll() {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        return entityManager.createQuery(SELECT_ALL).getResultList();
    }

    @Override
    public void update(int id, Bucket bucket) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(UPDATE)
                .setParameter("userId", bucket.getUserId())
                .setParameter("productId", bucket.getProductId())
                .setParameter("purchaseDate", bucket.getPurchaseDate())
                .setParameter("id", id);

        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        Bucket bucket = entityManager.find(Bucket.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(bucket);
        entityManager.getTransaction().commit();
    }

    @Override
    public int insert(Bucket bucket) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(bucket);
        entityManager.getTransaction().commit();

        return bucket.getId();
    }

    public void deleteByProductId(int productId) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.createQuery(DELETE_BY_PRODUCT_ID)
                .setParameter("productId", productId);
        entityManager.getTransaction().commit();
    }
}
