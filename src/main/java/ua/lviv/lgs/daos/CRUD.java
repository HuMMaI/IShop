package ua.lviv.lgs.daos;

import java.util.List;

public interface CRUD<T> {

    T getById(int id);
    List<T> getAll();
    void update(int id, T object);
    void delete(int id);
    int insert(T object);

}
