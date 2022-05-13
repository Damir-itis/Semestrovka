package com.damir.repositories;

import com.damir.models.Homework;
import com.damir.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    Optional<Homework> findByStudent(User user);
}
