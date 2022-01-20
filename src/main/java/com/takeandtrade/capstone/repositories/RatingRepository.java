package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository <Rating, Long> {

}
