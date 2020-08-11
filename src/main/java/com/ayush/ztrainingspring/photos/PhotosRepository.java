package com.ayush.ztrainingspring.photos;

import com.ayush.ztrainingspring.user_auth.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ayush.ztrainingspring.photos.Photo;

@Repository
public interface PhotosRepository extends CrudRepository<Photo, Integer> {

    List<Photo> findByCategoryEquals(String category);
    public Photo findByIdEquals(int id);


}
