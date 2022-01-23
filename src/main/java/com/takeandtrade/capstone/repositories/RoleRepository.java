package com.takeandtrade.capstone.repositories;

import com.takeandtrade.capstone.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleType (String role);
}
