package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername (String username);

    Optional<User> findUserByUsername(@Param("username") String username);
    Optional<User> findByEmail(@Param("email") String email);

    User findById (long userId);

}
