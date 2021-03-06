package ua.lviv.lgs.services;

import ua.lviv.lgs.daos.UserDao;
import ua.lviv.lgs.entities.User;
import ua.lviv.lgs.entities.UserRole;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserDao userDao;

    private static UserService userService;

    public UserService() {
        this.userDao = new UserDao();
    }

    public static UserService getInstance(){
        if (userService == null){
            userService = new UserService();
        }

        return userService;
    }

    public Optional<User> getById(int id){
        return userDao.getById(id);
    }

    public List<User> getAll(){
        return userDao.getAll();
    }

    public void delete(int id){
        userDao.delete(id);
    }



    public int insert(String email, String firstName, String lastName, String role, String password){
        return userDao.insert(
                User.builder()
                .setEmail(email)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole(role)
                .setPassword(password)
                .build()
        );
    }

    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    public Optional<User> getByEmailAndPassword(String email, String password) {
        return userDao.getByEmail(email).filter(user -> user.getPassword().equals(password));
    }

    public void update(int id, String email, String firstName, String lastName, String username, String password) {
        userDao.update(
                id,
                User.builder()
                .setEmail(email)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUsername(username)
                .setPassword(password)
                .build()
        );
    }

    public void edit(int id, String firstName, String lastName, String username, String bio, String phoneNumber, String address, String email, String age, String gender, String profession){
        userDao.edit(
                User.builder()
                .setId(id)
                .setEmail(email)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUsername(username)
                .setBio(bio)
                .setPhoneNumber(phoneNumber)
                .setAddress(address)
                .setAge(Integer.parseInt(age))
                .setGender(gender)
                .setProfession(profession)
                .build()
        );
    }
}
