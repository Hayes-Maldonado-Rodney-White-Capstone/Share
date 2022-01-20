package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.Message;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thymeleaf.expression.Messages;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Messages> getAllBySenderAndReceiver(SecurityProperties.User sender, SecurityProperties.User receiver);

}
