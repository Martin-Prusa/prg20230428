package cz.martin.beans;

import cz.martin.entities.Review;
import cz.martin.services.ReviewService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class NewReviewBean {
    @Inject
    private ReviewService reviewService;

    private Review review = new Review();

    public void add() {
        this.reviewService.addReview(review);
        this.review = new Review();
    }

    public Review getReview() {
        return review;
    }
}
