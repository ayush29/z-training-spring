package com.ayush.ztrainingspring.reviews.service;

import com.ayush.ztrainingspring.order.restaurants.Restaurantrepo;
import com.ayush.ztrainingspring.order.restaurants.Restaurants;
import com.ayush.ztrainingspring.reviews.dao.CommentRepository;
import com.ayush.ztrainingspring.reviews.dao.ReviewRepository;
import com.ayush.ztrainingspring.reviews.model.Comment;
import com.ayush.ztrainingspring.reviews.model.Review;
import com.ayush.ztrainingspring.reviews.requestHandlers.ReviewInfoHandler;
import com.ayush.ztrainingspring.user_auth.User;
import com.ayush.ztrainingspring.user_auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private Restaurantrepo restaurantrepo;

    private final static int REVIEWS_PER_PAGE = 2;

    //------------------------HELPER FUNCTIONS------------------------------//
    public Restaurants isRestaurantIdValid(int restaurantId) {
        return restaurantrepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("unable to find restaurant with id: " + restaurantId));
    }

    public User isUserIdValid(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user with id: " + userId + " not found"));
    }

    public User isUserIdValidAndLoggedIn(int userId) {
        return userRepository.findById(userId)
                .filter(User::getLoggedIn)
                .orElseThrow(() -> new RuntimeException("user with id: " + userId + " not found or is not logged in"));
    }

    //------------------------Service FUNCTIONS------------------------------//
    public List<Review> getAllReviews(int restaurantId) {
        Restaurants restaurant = isRestaurantIdValid(restaurantId);
        return reviewRepository.findByRestaurantOrderByCreatedTimeAsc(restaurant);
    }

    public Review addReview(ReviewInfoHandler reviewInfo, int restaurantId) {
        Review review = new Review();
        review.setText(reviewInfo.getText());

        if(reviewInfo.getRating() >= 1 && reviewInfo.getRating() <= 5)
           review.setRating(reviewInfo.getRating());
        else
            throw new RuntimeException("please provide a valid rating in between 1-5!!!");

        User user = isUserIdValidAndLoggedIn(reviewInfo.getUserId());
        review.setUser(user);
        Restaurants restaurant = isRestaurantIdValid(restaurantId);
        review.setRestaurant(restaurant);
        review.setReviewTags(reviewInfo.getReviewTags());
        review.getReviewTags().forEach(tag -> tag.setReview(review));
        review.setCreatedTime(Calendar.getInstance());
        return reviewRepository.save(review);
    }

    public int getUserNumReviews(int userId) {
        User user = isUserIdValid(userId);
        return reviewRepository.findByUser(user).map(List::size).orElse(0);
    }

    public long getFilteredNumReviews(int restaurantId, String filterOption, int userId) {
        Restaurants restaurant = isRestaurantIdValid(restaurantId);
        List<Review> reviews = reviewRepository.findByRestaurant(restaurant);
        switch (filterOption) {
            case "AllReviews":
                return reviews.size();
            case "MyReviews":
                isUserIdValid(userId);
                return reviews.stream().filter(review -> review.getUser().getId().equals(userId)).count();
            default:
                throw new RuntimeException("filtering option not found to get num reviews: " + filterOption);
        }
    }

    public List<Review> getAllFilteredSortedPageReviews(int restaurantId,
                                                        int userId,
                                                        String filterOption,
                                                        String sortOption,
                                                        int pageNum) {
        Restaurants restaurant = isRestaurantIdValid(restaurantId);
        List<Review> reviews = reviewRepository.findByRestaurant(restaurant);
        switch (filterOption) {
            case "AllReviews":
                break;
            case "MyReviews":
                isUserIdValidAndLoggedIn(userId);
                 reviews = reviews.stream()
                         .filter(review -> review.getUser().getId().equals(userId))
                         .collect(Collectors.toList());
                break;
            default:
                throw new RuntimeException("filtering option not found: " + filterOption);
        }
        //sorting reviews
        switch (sortOption) {
            case "NewestFirst":
                reviews.sort(Comparator.comparing(Review::getCreatedTime).reversed());
                break;
            case "OldestFirst":
                reviews.sort(Comparator.comparing(Review::getCreatedTime));
                break;
            case "HighestRated":
                reviews.sort(Comparator.comparing(Review::getRating).reversed());
                break;
            case "LowestRated":
                reviews.sort(Comparator.comparing(Review::getRating));
                break;
            default:
                throw new RuntimeException("sorting option not found: " + sortOption);
        }
        int totalNumPages = (int) Math.ceil((double) reviews.size()/(double) REVIEWS_PER_PAGE);
        if(totalNumPages == 0) return Collections.emptyList();
        pageNum = Math.min(totalNumPages, Math.max(1, pageNum));
        int startInd = (pageNum - 1) * REVIEWS_PER_PAGE;
        int endInd = Math.min(startInd + REVIEWS_PER_PAGE, reviews.size());
        System.out.println(totalNumPages + " " + pageNum + " " + startInd + " " + endInd);
        return reviews.subList(startInd, endInd);
    }

    public List<Comment> getComments(int reviewId) {
        return reviewRepository.findById(reviewId)
                        .map(Review::getComments)
                        .orElseThrow(
                                () -> new RuntimeException("review with id: " + reviewId +
                                        " not found to retrieve comments")
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
        User user = isUserIdValidAndLoggedIn(userId);
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

    public int addLike(int reviewId, int userId) {
        isUserIdValidAndLoggedIn(userId);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("review not found to increase a like: " + reviewId));
        int likes = review.getLikes();
        review.setLikes(likes+1);
        return reviewRepository.save(review).getLikes();
    }
}