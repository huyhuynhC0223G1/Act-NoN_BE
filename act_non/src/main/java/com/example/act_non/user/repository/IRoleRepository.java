package com.example.act_non.user.repository;

import com.example.act_non.user.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Roles, Long> {
}
