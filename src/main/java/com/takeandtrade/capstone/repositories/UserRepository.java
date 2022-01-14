package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername (String username);

}
