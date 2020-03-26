package ua.lviv.lgs.services;

import ua.lviv.lgs.daos.ProductDao;
import ua.lviv.lgs.entities.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProductService {
    private ProductDao productDao;

    private static ProductService productService;

    public ProductService() {
        this.productDao = new ProductDao();
    }

    public static ProductService getInstance(){
        if (productService == null){
            productService = new ProductService();
        }

        return productService;
    }

    public Optional<Product> getById(int id){
        return productDao.getById(id);
    }

    public List<Product> getAll(){
        return productDao.getAll();
    }

    public void delete(int id){
        productDao.delete(id);
    }

    public void update(int id, Product product){
        productDao.update(id, product);
    }

    public int insert(String name, String description, double price){
        return productDao.insert(
                Product.builder()
                        .setName(name)
                        .setDescription(description)
                        .setPrice(price)
                        .build()
        );
    }

    public List<Product> getByIds(Set<Integer> productIds) {
        return productDao.getByIds(productIds);
    }
}
