package ua.lviv.lgs.services;

import ua.lviv.lgs.daos.BucketDao;
import ua.lviv.lgs.entities.Bucket;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BucketService {
    private BucketDao bucketDao;

    private static BucketService bucketService;

    public BucketService() {
        this.bucketDao = new BucketDao();
    }

    public static BucketService getInstance(){
        if (bucketService == null){
            bucketService = new BucketService();
        }

        return bucketService;
    }

    public Optional<Bucket> getById(int id){
        return bucketDao.getById(id);
    }

    public Optional<List<Bucket>> getAll(){
        return bucketDao.getAll();
    }

    public void delete(int id){
        bucketDao.delete(id);
    }

    public void update(int id, Bucket bucket){
        bucketDao.update(id, bucket);
    }

    public int insert(int userId, int productId, Date purchaseDate){
        return bucketDao.insert(
                Bucket.builder()
                        .setUserId(userId)
                        .setProductId(productId)
                        .setPurchaseDate(purchaseDate)
                        .build()
        );
    }
}
