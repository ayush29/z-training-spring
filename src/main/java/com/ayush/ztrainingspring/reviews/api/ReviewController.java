package com.ayush.ztrainingspring.reviews.api;

import com.ayush.ztrainingspring.reviews.model.Comment;
import com.ayush.ztrainingspring.reviews.model.Review;
import com.ayush.ztrainingspring.reviews.requestHandlers.ReviewInfoHandler;
import com.ayush.ztrainingspring.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "http://localhost:8081" })
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

    @GetMapping("/user/{userId}/filter/{filterOption}/num-reviews")
    public long getNumReviews(@PathVariable("userId") int userId,
                              @PathVariable("filterOption") String filterOption) {
        return reviewService.getNumReviews(userId, filterOption);
    }

    @GetMapping("/sort/{option}")
    public List<Review> getAllSortedReviews(@PathVariable("option") String option) {
        return reviewService.getAllSortedReviews(option);
    }

    @GetMapping("/sort/{option}/page/{pageNum}")
    public List<Review> getAllSortedPageReviews(@PathVariable("option") String option,
                                                @PathVariable("pageNum") int pageNum) {
        return reviewService.getAllSortedPageReviews(option, pageNum);
    }

    @GetMapping("/user/{userId}/filter/{filterOption}/sort/{sortOption}/page/{pageNum}")
    public List<Review> getAllSortedPageReviews(@PathVariable("userId") int userId,
                                                @PathVariable("filterOption") String filterOption,
                                                @PathVariable("sortOption") String sortOption,
                                                @PathVariable("pageNum") int pageNum) {
        System.out.println("controller" + userId);
        return reviewService.getAllFilteredSortedPageReviews(userId, filterOption, sortOption, pageNum);
    }

    /**
     * @param id -> the possible review id
     * @return comments of review with {id}
     */
    @GetMapping(path = "/{revId}/comments")
    public List<Comment> getComments(@PathVariable("revId") int id) {
        return reviewService.getComments(id);
    }

    @PostMapping(path = "/{revId}/comments")
    public Comment addComment(@PathVariable("revId") int reviewId, @RequestBody Map<String,String> commentInfo) {
        return reviewService.addComment(reviewId, commentInfo);
    }

    @PostMapping(path = "/{id}/user/{userId}/like")
    public int addLike(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        return reviewService.addLike(id, userId);
    }

//    @GetMapping(path = "/user/{userId}")
//    public List<Review> getUserReviews(@PathVariable("userId") int userId) {
//        return reviewService.getUserReviews(userId);
//    }
}