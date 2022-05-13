package com.damir.repositories;

import com.damir.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = ?1")
    Optional<User> findByUsername(String email);


    @Query("select u from User u where u.confirmCode = ?1")
    Optional<User> findByConfirmCode(String confirmCode);



}
