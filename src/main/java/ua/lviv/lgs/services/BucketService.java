package ua.lviv.lgs.services;

import ua.lviv.lgs.daos.BucketDao;
import ua.lviv.lgs.entities.Bucket;

import java.util.List;

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

    public Bucket getById(int id){
        return bucketDao.getById(id);
    }

    public List<Bucket> getAll(){
        return bucketDao.getAll();
    }

    public void delete(int id){
        bucketDao.delete(id);
    }

    public void update(int id, Bucket bucket){
        bucketDao.update(id, bucket);
    }

    public int insert(Bucket bucket){
        return bucketDao.insert(bucket);
    }
}
