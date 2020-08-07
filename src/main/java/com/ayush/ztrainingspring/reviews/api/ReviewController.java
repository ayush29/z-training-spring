//package com.ayush.ztrainingspring.reviews.api;
//
//import com.ayush.ztrainingspring.reviews.model.Comment;
//import com.ayush.ztrainingspring.reviews.model.Review;
//import com.ayush.ztrainingspring.reviews.service.ReviewService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.MalformedURLException;
//import java.util.List;
//import java.util.UUID;
//
//@CrossOrigin(origins = { "http://localhost:8081" })
//@RestController
//public class ReviewController {
//    private final ReviewService reviewService;
//
//    @Autowired
//    public ReviewController(ReviewService reviewService) {
//        this.reviewService = reviewService;
//    }
//
//    @PostMapping("/reviews")
//    public int addReview(@RequestBody Review review) {
//        return reviewService.addReview(review);
//    }
//
//    @GetMapping("/reviews")
//    public List<Review> getAllReviews() {
//        return reviewService.getAllReviews();
//    }
//
//    @GetMapping("/reviews/numReviews")
//    public int getNumReviews() {
//        return reviewService.getNumReviews();
//    }
//
//
//    @GetMapping("/reviews/sort/{option}")
//    public List<Review> getAllSortedReviews(@PathVariable("option") String option) {
//        return reviewService.getAllSortedReviews(option);
//    }
//
//    @GetMapping("/reviews/sort/{option}/{pageNum}")
//    public List<Review> getAllSortedPageReviews(@PathVariable("option") String option, @PathVariable("pageNum") int pageNum) {
//        return reviewService.getAllSortedPageReviews(option, pageNum);
//    }
//
//    @PostMapping(path = "/reviews/comment/{id}")
//    public Comment addComment(@PathVariable("id") UUID id, @RequestBody Comment comment) throws MalformedURLException {
//        return reviewService.addComment(id, comment);
//    }
//
//    @GetMapping(path = "/reviews/comment/{id}")
//    public List<Comment> getComments(@PathVariable("id") UUID id) {
//        return reviewService.getComments(id);
//    }
//
//    @PostMapping(path = "/reviews/like/{id}")
//    public int addLike(@PathVariable("id") UUID id) {
//        return reviewService.addLike(id);
//    }
//}
