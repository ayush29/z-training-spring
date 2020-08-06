package com.ayush.ztrainingspring.reviews.dao;

import com.ayush.ztrainingspring.reviews.model.Comment;
import com.ayush.ztrainingspring.reviews.model.Review;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.util.*;

@Repository("dummyDB")
public class DummyReviewDatabase implements ReviewDao{
    private static List<Review> DB = new ArrayList<Review>(){  // add temporary reviews
        {
            try {
                add(new Review(1, "review 1"));
                add(new Review(2, "review 2"));
                add(new Review(3, "review 3"));
                add(new Review(4, "review 4"));
                add(new Review(5, "review 5"));
                add(new Review(6, "review 6"));
                add(new Review(7, "review 7"));
                add(new Review(8, "review 8"));
                add(new Review(9, "review 9"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    public int addReview(Review review) {
        DB.add(review);
        return 0;
    }

    @Override
    public List<Review> getAllReviews() {
        return DB;
    }

    @Override
    public List<Review> getAllSortedReviews(String option) {
        switch (option) {
            case "NewestFirst":
                DB.sort((o1, o2) -> o2.getReviewRatingTime().compareTo(o1.getReviewRatingTime()));
                break;
            case "OldestFirst":
                DB.sort(Comparator.comparing(Review::getReviewRatingTime));
                break;
            case "HighestRated":
                DB.sort((o1, o2) -> o2.getReviewRatingNum() - o1.getReviewRatingNum());
                break;
            case "LowestRated":
                DB.sort(Comparator.comparingInt(Review::getReviewRatingNum));
                break;
            default:
                System.out.println("Sorting Option not found");
        }
        return DB;
    }

    @Override
    public int getNumReviews() {
        return DB.size();
    }

    @Override
    public Comment addComment(UUID id, Comment comment) throws MalformedURLException {
        for (Review rev : DB) {
            if (rev.getId().equals(id)) {
                rev.addComment(comment);
                break;
            }
        }
        return comment;
    }

    @Override
    public List<Comment> getComments(UUID id) {
        for (Review rev : DB) {
            if (rev.getId().equals(id)) {
                return rev.getCommentList();
            }
        }
//        Optional<Review> first = DB.stream()
//                .filter(rev -> rev.getId().equals(id))
//                .findFirst()
//                .ifPresent(Review::getCommentList);
        return null;
    }

    @Override
    public int addLike(UUID id) {
        for (Review rev : DB) {
            if (rev.getId().equals(id)) {
                rev.setReviewNumLikes();
                return rev.getReviewNumLikes();
            }
        }
        return 0;
    }
}
