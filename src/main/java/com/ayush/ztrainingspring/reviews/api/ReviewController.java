package com.ayush.ztrainingspring.reviews.api;

import com.ayush.ztrainingspring.reviews.model.Comment;
import com.ayush.ztrainingspring.reviews.model.Review;
import com.ayush.ztrainingspring.reviews.requestHandlers.ReviewInfoHandler;
import com.ayush.ztrainingspring.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/restaurant/{restaurantId}")
    public List<Review> getAllReviews(@PathVariable("restaurantId") int restaurantId) {
        return reviewService.getAllReviews(restaurantId);
    }

    @PostMapping("/restaurant/{restaurantId}")
    public Review addReview(@RequestBody ReviewInfoHandler reviewInfo, @PathVariable("restaurantId") int restaurantId) {
        return reviewService.addReview(reviewInfo, restaurantId);
    }

    @GetMapping(path = "/user/{userId}/num-reviews")
    public int getUserNumReviews(@PathVariable("userId") int userId) {
        return reviewService.getUserNumReviews(userId);
    }

    // needed for pagination in front end
    @GetMapping("/restaurant/{restaurantId}/filter/{filterOption}/user/{userId}/num-reviews")
    public long getFilteredNumReviews(@PathVariable("restaurantId") int restaurantId,
                                      @PathVariable("filterOption") String filterOption,
                                      @PathVariable("userId") int userId) {
        return reviewService.getFilteredNumReviews(restaurantId, filterOption, userId);
    }

    @GetMapping("/restaurant/{restaurantId}/filter/{filterOption}/user/{userId}/sort/{sortOption}/page/{pageNum}")
    public List<Review> getAllSortedPageReviews(@PathVariable("restaurantId") int restaurantId,
                                                @PathVariable("userId") int userId,
                                                @PathVariable("filterOption") String filterOption,
                                                @PathVariable("sortOption") String sortOption,
                                                @PathVariable("pageNum") int pageNum) {
        return reviewService.getAllFilteredSortedPageReviews(restaurantId, userId, filterOption, sortOption, pageNum);
    }

    @GetMapping(path = "/{reviewId}/comments")
    public List<Comment> getComments(@PathVariable("reviewId") int reviewId) {
        return reviewService.getComments(reviewId);
    }

    @PostMapping(path = "/{reviewId}/comments")
    public Comment addComment(@PathVariable("reviewId") int reviewId, @RequestBody Map<String,String> commentInfo) {
        return reviewService.addComment(reviewId, commentInfo);
    }

    @PostMapping(path = "/{reviewId}/user/{userId}/like")
    public int addLike(@PathVariable("reviewId") int reviewId, @PathVariable("userId") int userId) {
        return reviewService.addLike(reviewId, userId);
    }
}