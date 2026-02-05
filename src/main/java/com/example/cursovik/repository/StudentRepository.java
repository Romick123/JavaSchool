package com.example.cursovik.repository;

import com.example.cursovik.entity.ClassRoom;
import com.example.cursovik.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByClassRoom(ClassRoom classRoom);
}
