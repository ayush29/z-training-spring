package com.ayush.ztrainingspring.reviews.dao;

import com.ayush.ztrainingspring.reviews.model.ReviewTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewTagRepository extends JpaRepository<ReviewTag, Integer> {
}
