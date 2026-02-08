package com.example.cursovik.repository;

import com.example.cursovik.entity.ClassRoom;
import com.example.cursovik.entity.Homework;
import com.example.cursovik.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {

    List<Homework> findByClassRoom(ClassRoom classRoom);
    List<Homework> findBySubject(Subject subject);
}
