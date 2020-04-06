package ua.lviv.lgs.daos;

import org.apache.log4j.Logger;
import ua.lviv.lgs.EntityManagerUtils;
import ua.lviv.lgs.entities.Product;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProductDao implements CRUD<Product> {
    private static Logger LOG = Logger.getLogger(ProductDao.class);

    private static final String SELECT_ALL = "SELECT p FROM Product p";
    private static final String UPDATE =
            "UPDATE Product p SET p.name = :name, p.description = :description, p.price = :price WHERE p.id = :id";
    private static final String SELECT_ALL_IN = "SELECT p FROM Product p WHERE p.id IN (:productIds)";

    public ProductDao() {
    }

    @Override
    public Optional<Product> getById(int id) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAll() {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        return entityManager.createQuery(SELECT_ALL).getResultList();
    }

    @Override
    public void update(int id, Product product) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(UPDATE)
                .setParameter("name", product.getName())
                .setParameter("description", product.getDescription())
                .setParameter("price", product.getPrice())
                .setParameter("id", id);

        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        Product product = entityManager.find(Product.class, id);

        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public int insert(Product product) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        String message = String.format("Will create new product with name = %s", product.getName());
        LOG.debug(message);

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();

        return product.getId();
    }

    @SuppressWarnings("unchecked")
    public List<Product> getByIds(Set<Integer> productIds) {
        List<Product> products = new ArrayList<>();

        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        entityManager.getTransaction().begin();

        if(!productIds.isEmpty()){
            products = entityManager.createQuery(SELECT_ALL_IN)
                    .setParameter("productIds", productIds)
                    .getResultList();
        }

        entityManager.getTransaction().commit();


        return products;
    }
}
