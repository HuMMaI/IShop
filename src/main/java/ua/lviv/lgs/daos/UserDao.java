package ua.lviv.lgs.daos;

import org.apache.log4j.Logger;
import ua.lviv.lgs.EntityManagerUtils;
import ua.lviv.lgs.entities.User;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao implements CRUD<User> {
    private static Logger LOG = Logger.getLogger(UserDao.class);

    private static final String SELECT_ALL = "SELECT u FROM User u";
    private static final String UPDATE =
            "UPDATE User u SET u.email = :email, u.firstName = :firstName, u.lastName = :lastName, u.role = :role, u.password = :password WHERE u.id = :id";
    private static final String SELECT_BY_EMAIL = "SELECT u FROM User u WHERE u.email = :email";
    private static final String EDIT =
            "UPDATE User u SET u.email = :email, u.firstName = :firstName, u.lastName = :lastName, u.username = :username, u.bio = :bio, u.phoneNumber = :phoneNumber, u.address = :address, u.age = :age, u.gender = :gender, u.profession = :profession WHERE u.id = :id";

    public UserDao() {
    }


    @Override
    public Optional<User> getById(int id) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        return entityManager.createQuery(SELECT_ALL).getResultList();
    }

    @Override
    public void update(int id, User user) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(UPDATE)
                .setParameter("email", user.getEmail())
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("role", user.getRole())
                .setParameter("password", user.getPassword())
                .setParameter("id", id);

        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        User user = entityManager.find(User.class, id);

        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public int insert(User user) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        String message = String.format("Will create new user with email = %s", user.getEmail());
        LOG.debug(message);

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        return user.getId();
    }

    public Optional<User> getByEmail(String email) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        entityManager.getTransaction().begin();

        User user = (User) entityManager.createQuery(SELECT_BY_EMAIL)
                .setParameter("email", email)
                .getSingleResult();

        entityManager.getTransaction().commit();

        return Optional.ofNullable(user);
    }

    public void edit(User user) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(EDIT)
                .setParameter("email", user.getEmail())
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("username", user.getUsername())
                .setParameter("bio", user.getBio())
                .setParameter("phoneNumber", user.getPhoneNumber())
                .setParameter("address", user.getAddress())
                .setParameter("age", user.getAge())
                .setParameter("gender", user.getGender())
                .setParameter("profession", user.getProfession())
                .setParameter("id", user.getId());

        entityManager.getTransaction().commit();
    }
}
