package com.example.photos_sql_2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.photos_sql_2.Photo;

@Repository
public interface PhotosRepository extends CrudRepository<Photo, Integer> {

    List<Photo> findByCategoryEquals(String category);

}
