package cz.martin.beans;

import cz.martin.entities.Review;
import cz.martin.services.ReviewService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class EditReviewBean implements Serializable {
    @Inject
    private ReviewService reviewService;

    private int id;
    private Review review;

    public void edit() {
        this.reviewService.editReview(review);
    }

    public List<Integer> getNumbers() {
        List<Integer> l = new ArrayList<>();
        for (int i =1 ; i < 5; i++) {
            l.add(i);
        }
        return l;
    }

    public Review getReview() {
        this.id = getId();
        if(id == -1) return new Review();
        if(this.review == null || this.id != this.review.getId()) {
            this.review = reviewService.getReviewById(id);
        }
        return review;
    }

    private int getId() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if(id != null && !id.equals("")) return Integer.parseInt(id);
        return -1;
    }
}
