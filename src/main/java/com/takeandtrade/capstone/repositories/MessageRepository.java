package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.Message;
import com.takeandtrade.capstone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
