package com.ayush.ztrainingspring.photos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ayush.ztrainingspring.photos.Photo;

@Repository
public interface PhotosRepository extends CrudRepository<Photo, Integer> {

    List<Photo> findByCategoryEquals(String category);

}
