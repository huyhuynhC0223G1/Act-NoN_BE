package com.example.act_non.user.repository;

import com.example.act_non.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {
Users findByUsernameAndFlagDeleteIsFalse(String username);

//    @Modifying
//    @Transactional
//    @Query(value = "update users set flag_online = 1 where username = :userName",nativeQuery = true)
//    Integer updateUserIsOnline(@Param("userName") String userName);
    @Modifying
    @Transactional
    @Query(value = "update users set flag_online = 0 where username = :userName",nativeQuery = true)
    Integer updateUserIsOffline(@Param("userName") String userName);

    Users findByUsername(String username);
}
