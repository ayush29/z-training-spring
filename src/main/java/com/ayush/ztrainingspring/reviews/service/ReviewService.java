package com.ayush.ztrainingspring.reviews.service;

import com.ayush.ztrainingspring.reviews.dao.CommentRepository;
import com.ayush.ztrainingspring.reviews.dao.ReviewRepository;
import com.ayush.ztrainingspring.reviews.model.Comment;
import com.ayush.ztrainingspring.reviews.model.Review;
import com.ayush.ztrainingspring.reviews.requestHandlers.ReviewInfoHandler;
import com.ayush.ztrainingspring.user_auth.User;
import com.ayush.ztrainingspring.user_auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Review addReview(ReviewInfoHandler reviewInfo) {
        Review review = new Review();
        review.setText(reviewInfo.getText());

        if(reviewInfo.getRating() >= 1 && reviewInfo.getRating() <= 5)
           review.setRating(reviewInfo.getRating());
        else
            throw new RuntimeException("please provide a valid rating!!!");

        User user = userRepository.findById(reviewInfo.getUserId())
                                  .filter(User::getLoggedIn)
                                  .orElseThrow(
                                          () -> new RuntimeException("user with id: " + reviewInfo.getUserId() +
                                                  " not found to add review or is not logged in")
                                  );
        review.setUser(user);
        review.setReviewTags(reviewInfo.getReviewTags());
        review.getReviewTags().forEach(tag -> tag.setReview(review));
        review.setCreatedTime(Calendar.getInstance());
        return reviewRepository.save(review);
    }

    public int getUserNumReviews(int userId) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(
                                      () -> new RuntimeException("user not found to get his/her" +
                                                  " num reviews -> user id: " + userId)
                                  );
        return reviewRepository.findByUserId(userId)
                .map(List::size)
                .orElseGet(
                        () -> 0
                );
    }

    public long getNumReviews(int userId, String filterOption) {
        switch (filterOption) {
            case "AllReviews":
                return reviewRepository.count();
            case "MyReviews":
                return getUserNumReviews(userId);
            default:
                throw new RuntimeException("filtering option not found to get num reviews: " + filterOption);
        }
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

    public List<Review> getAllFilteredSortedPageReviews(int userId,
                                                        String filterOption,
                                                        String sortOption,
                                                        int pageNum) {
        List<Review> allSortedReviews = getAllSortedReviews(sortOption);
        switch (filterOption) {
            case "AllReviews":
                break;
            case "MyReviews":
                User user = userRepository.findById(userId)
                                          .filter(User::getLoggedIn)
                                          .orElseThrow(
                                              () -> new RuntimeException("user not found or not logged in " +
                                                      "to filter his/her reviews -> user id: " + userId)
                                          );
                allSortedReviews = allSortedReviews.stream()
                                                   .filter(review -> review.getUser().getId().equals(userId))
                                                   .collect(Collectors.toList());
                System.out.println(user);
                break;
            default:
                throw new RuntimeException("filtering option not found: " + filterOption);
        }
        int totalNumPages = (int) Math.ceil((double) allSortedReviews.size()/(double) REVIEWS_PER_PAGE);
        pageNum = Math.min(totalNumPages, Math.max(1, pageNum));
        int startInd = (pageNum - 1) * REVIEWS_PER_PAGE;
        int endInd = Math.min(startInd + REVIEWS_PER_PAGE, allSortedReviews.size());
        return allSortedReviews.subList(startInd, endInd);
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
        if(commentInfo.containsKey("text")) {
            String commentText = commentInfo.get("text").trim();
            if(commentText.equals(""))
                throw new RuntimeException("comment should have some valid text and not spaces!!!");
            else
                comment.setText(commentText);
        }
        else
            throw new RuntimeException("comment needs to have text!!!");

        int userId;
        if(commentInfo.containsKey("user_id"))
            userId = Integer.parseInt(commentInfo.get("user_id"));
        else
            throw new RuntimeException("comment comes from a ghost user!!!");
        User user = userRepository.findById(userId)
                                  .filter(User::getLoggedIn)
                                  .orElseThrow(
                                          () -> new RuntimeException("user not found or not logged in to add comment: "
                                                  +  commentInfo.get("user_id"))
                                  );
        comment.setUser(user);

        Review review = reviewRepository.findById(reviewId)
                                        .orElseThrow(
                                                () -> new RuntimeException("review not found to add comment: " +
                                                        reviewId)
                                        );
        System.out.println(review.getId());
        comment.setReview(review);
        comment.setCreatedTime(Calendar.getInstance());
        return commentRepository.save(comment);
    }

    public int addLike(int id, int userId) {
        userRepository.findById(userId)
                                  .filter(User::getLoggedIn)
                                  .orElseThrow(() -> new RuntimeException("user not found or not logged in " +
                                          "to like the review: "));

        Review review = reviewRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("review not found to increase a like: " +
                                String.valueOf(id))
                );
        int likes = review.getLikes();
        review.setLikes(likes+1);
        return reviewRepository.save(review).getLikes();
    }

//    public List<Review> getUserReviews(int userId) {
//        userRepository.findById(userId)
//                .orElseThrow(
//                        () -> new RuntimeException("user not found to get his/her reviews -> user id: " + userId)
//                );
//        return reviewRepository.findByUserId(userId)
//                               .orElseGet(ArrayList::new);
//    }

}