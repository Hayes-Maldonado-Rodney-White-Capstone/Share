package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
//    Optional<Request> findById(Long id);
    Optional<Request> findByBeginDate(Date beginDate);
//
//    Request findByApprover1(String approver1);
//    Request findByRequesteditems(Long itemId);

}
