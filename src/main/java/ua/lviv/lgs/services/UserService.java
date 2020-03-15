package ua.lviv.lgs.services;

import ua.lviv.lgs.daos.UserDao;
import ua.lviv.lgs.entities.User;

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

    public Optional<List<User>> getAll(){
        return userDao.getAll();
    }

    public void delete(int id){
        userDao.delete(id);
    }

    public void update(int id, User user){
        userDao.update(id, user);
    }

    public int insert(User user){
        return userDao.insert(user);
    }

    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
