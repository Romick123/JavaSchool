package com.example.cursovik.repository;

import com.example.cursovik.entity.Grade;
import com.example.cursovik.entity.Student;
import com.example.cursovik.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(Student student);
    List<Grade> findByStudentAndSubject(Student student, Subject subject);
    List<Grade> findBySubject(Subject subject);
}
