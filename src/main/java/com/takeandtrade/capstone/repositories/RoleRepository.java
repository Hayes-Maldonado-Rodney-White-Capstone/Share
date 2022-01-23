package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole (String role);
}
