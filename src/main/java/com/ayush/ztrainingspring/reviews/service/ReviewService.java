package com.ayush.ztrainingspring.reviews.service;

import com.ayush.ztrainingspring.reviews.dao.CommentRepository;
import com.ayush.ztrainingspring.reviews.dao.ReviewRepository;
import com.ayush.ztrainingspring.reviews.model.Comment;
import com.ayush.ztrainingspring.reviews.model.Review;
import com.ayush.ztrainingspring.user_auth.User;
import com.ayush.ztrainingspring.user_auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    private final static int REVIEWS_PER_PAGE = 2;

    public List<Review> getAllReviews() {
        return reviewRepository.findByOrderByCreatedTimeDesc();
    }

    public Review addReview(Map<String, String> reviewInfo) {
        Review review = new Review();
        review.setText(reviewInfo.getOrDefault("text", ""));

        if(reviewInfo.containsKey("rating"))
           review.setRating(Integer.parseInt(reviewInfo.get("rating")));
        else
            throw new RuntimeException("please rate the order!!!");

        int userId;
        if(reviewInfo.containsKey("user_id"))
            userId = Integer.parseInt(reviewInfo.get("user_id"));
        else
            throw new RuntimeException("the review request came from a ghost user!!!");
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("user not found to add review: " + reviewInfo.get("user_id"))
                );
        review.setUser(user);

        review.setCreatedTime(Calendar.getInstance());
        return reviewRepository.save(review);
    }

    public long getNumReviews() {
        return reviewRepository.count();
    }

    public List<Review> getAllSortedReviews(String option) {
        switch (option) {
            case "NewestFirst":
                return reviewRepository.findByOrderByCreatedTimeDesc();
            case "OldestFirst":
                return reviewRepository.findByOrderByCreatedTimeAsc();
            case "HighestRated":
                return reviewRepository.findByOrderByRatingDescCreatedTimeDesc();
            case "LowestRated":
                return reviewRepository.findByOrderByRatingAscCreatedTimeDesc();
            default:
                throw new RuntimeException("sorting option not found: " + option);
        }
    }

    public List<Review> getAllSortedPageReviews(String option, int pageNum) {
        List<Review> sortedReviews = getAllSortedReviews(option);
        int totalNumPages = (int) Math.ceil((double) sortedReviews.size()/(double) REVIEWS_PER_PAGE);
        pageNum = Math.min(totalNumPages, Math.max(1, pageNum));
        int startInd = (pageNum - 1) * REVIEWS_PER_PAGE;
        int endInd = Math.min(startInd + REVIEWS_PER_PAGE, sortedReviews.size());
        return sortedReviews.subList(startInd, endInd);
    }

    public List<Comment> getComments(int id) {
        return reviewRepository.findById(id)
                        .map(Review::getComments)
                        .orElseThrow(
                                () -> new RuntimeException("review with id: " + id + " not found to retrieve comments")
                        );
    }

    public Comment addComment(int reviewId, Map<String, String> commentInfo) {
        Comment comment = new Comment();
        if(commentInfo.containsKey("text"))
            comment.setText(commentInfo.get("text"));
        else
            throw new RuntimeException("comment needs to have text!!!");

        int userId;
        if(commentInfo.containsKey("user_id"))
            userId = Integer.parseInt(commentInfo.get("user_id"));
        else
            throw new RuntimeException("comment comes from a ghost user!!!");
        User user = userRepository.findById(userId)
                                  .orElseThrow(
                                          () -> new RuntimeException("user not found to add comment: " +
                                                  commentInfo.get("user_id"))
                                  );
        comment.setUser(user);

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new RuntimeException("review not found to add comment: " + reviewId)
        );
        comment.setReview(review);
        comment.setCreatedTime(Calendar.getInstance());
        return commentRepository.save(comment);
    }

    public Review addLike(int id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("review not found to increase a like: " +
                                String.valueOf(id))
                );
        int likes = review.getLikes();
        review.setLikes(likes+1);
        return reviewRepository.save(review);
    }

//    public Review getUserNumReviews(int id) {
//    }
}