package com.example.cursovik.repository;

import com.example.cursovik.entity.Teacher;
import com.example.cursovik.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByUser(User user);
}
