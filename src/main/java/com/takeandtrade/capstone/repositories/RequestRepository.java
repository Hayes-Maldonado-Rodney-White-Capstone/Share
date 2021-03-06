package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findByBeginDate(Date beginDate);

    Request findAllByApprover1(String approver1);

}
