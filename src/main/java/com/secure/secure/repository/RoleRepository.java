package com.secure.secure.repository;

import com.secure.secure.enums.AppRole;
import com.secure.secure.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(AppRole roleName);

}
