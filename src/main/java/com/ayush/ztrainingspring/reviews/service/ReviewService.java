//package com.ayush.ztrainingspring.reviews.service;
//
//import com.ayush.ztrainingspring.reviews.dao.ReviewDao;
//import com.ayush.ztrainingspring.reviews.model.Comment;
//import com.ayush.ztrainingspring.reviews.model.Review;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.net.MalformedURLException;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class ReviewService {
//    private final ReviewDao reviewDao;
//    private final static int REVIEWS_PER_PAGE = 2;
//
//    @Autowired
//    public ReviewService(@Qualifier("dummyDB") ReviewDao reviewDao) {
//        this.reviewDao = reviewDao;
//    }
//
//    public int addReview(Review review) {
//        return reviewDao.addReview(review);
//    }
//
//    public List<Review> getAllReviews() {
//        return reviewDao.getAllReviews();
//    }
//
//    public List<Review> getAllSortedReviews(String option) {
//        return reviewDao.getAllSortedReviews(option);
//    }
//
//    public List<Review> getAllSortedPageReviews(String option, int pageNum) {
//        List<Review> sortedDB = getAllSortedReviews(option);
//        int totalNumPages = (int) Math.ceil((double) sortedDB.size()/(double) REVIEWS_PER_PAGE);
//        pageNum = Math.min(totalNumPages, Math.max(1, pageNum));
//        int startInd = (pageNum - 1) * REVIEWS_PER_PAGE;
//        int endInd = Math.min(startInd + REVIEWS_PER_PAGE, sortedDB.size());
//        return sortedDB.subList(startInd, endInd);
//    }
//
//    public Comment addComment(UUID id, Comment comment) throws MalformedURLException {
//        return reviewDao.addComment(id, comment);
//    }
//
//    public List<Comment> getComments(UUID id) {
//        return reviewDao.getComments(id);
//    }
//
//    public int addLike(UUID id) {
//        return reviewDao.addLike(id);
//    }
//
//    public int getNumReviews() {
//        return reviewDao.getNumReviews();
//    }
//}
