package com.example.act_non.user.repository;

import com.example.act_non.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {
Users findByUsername(String username);
}
