package cz.martin.services;

import cz.martin.entities.Review;
import cz.martin.repositories.ReviewRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named
public class ReviewService {
    @Inject
    private ReviewRepository reviewRepository;

    public void addReview(Review review) {
        this.reviewRepository.addReview(review);
    }

    public void deleteReview(int id) {
        this.reviewRepository.deleteReview(id);
    }

    public List<Review> getReviews() {
        return this.reviewRepository.getReviews();
    }

    public Review getReviewById(int id) {
        return this.reviewRepository.getReviewById(id);
    }

    public void editReview(Review review) {
        this.reviewRepository.editReview(review);
    }
}
