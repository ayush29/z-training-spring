package com.ayush.ztrainingspring.reviews.api;

import com.ayush.ztrainingspring.reviews.model.Comment;
import com.ayush.ztrainingspring.reviews.model.Review;
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

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping
    public Review addReview(@RequestBody Map<String, String> reviewInfo) {
        return reviewService.addReview(reviewInfo);
    }

    @GetMapping("/num-reviews")
    public long getNumReviews() {
        return reviewService.getNumReviews();
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

    /**
     * @param id -> the possible review id
     * @return comments of review with {id}
     */
    @GetMapping(path = "/{id}/comments")
    public List<Comment> getComments(@PathVariable("id") int id) {
        return reviewService.getComments(id);
    }

    @PostMapping(path = "/{id}/comments")
    public Comment addComment(@PathVariable("id") int reviewId, @RequestBody Map<String,String> commentInfo) {
        return reviewService.addComment(reviewId, commentInfo);
    }

    @PostMapping(path = "/{id}/likes")
    public Review addLike(@PathVariable("id") int id) {
        return reviewService.addLike(id);
    }
}