package ua.lviv.lgs.services;

import ua.lviv.lgs.daos.ProductDao;
import ua.lviv.lgs.entities.Product;

import java.util.List;

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

    public Product getById(int id){
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

    public int insert(Product product){
        return productDao.insert(product);
    }
}
