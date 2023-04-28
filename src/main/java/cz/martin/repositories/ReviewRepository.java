package cz.martin.repositories;

import cz.martin.entities.Review;
import cz.martin.services.EntityManagerFactoryProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class ReviewRepository {
    @Inject
    private EntityManagerFactoryProvider emfp;

    public void addReview(Review review) {
        EntityManager em = emfp.getEmf().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(review);
        et.commit();
        em.close();
    }

    public List<Review> getReviews() {
        EntityManager em = emfp.getEmf().createEntityManager();
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review AS r", Review.class);
        List<Review> reviews = query.getResultList();
        em.close();
        return reviews;
    }

    public void deleteReview(int id) {
        EntityManager em = emfp.getEmf().createEntityManager();
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review AS r WHERE r.id = :id", Review.class);
        query.setParameter("id", id);
        Review r = query.getSingleResult();

        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(r);
        et.commit();
        em.close();
    }

    public Review getReviewById(int id) {
        EntityManager em = emfp.getEmf().createEntityManager();
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review AS r WHERE r.id = :id", Review.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public void editReview(Review review) {
        EntityManager em = emfp.getEmf().createEntityManager();
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review AS r WHERE r.id = :id", Review.class);
        query.setParameter("id", review.getId());
        Review r = query.getSingleResult();


        EntityTransaction et = em.getTransaction();
        et.begin();
        r.setText(review.getText());
        em.persist(r);
        et.commit();
        em.close();
    }
}
