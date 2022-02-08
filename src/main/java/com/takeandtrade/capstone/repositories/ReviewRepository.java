package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(rating) FROM Review WHERE producer.id = ?1")
    public Double findRatingAverage(Long producerId);

}
