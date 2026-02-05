package com.example.cursovik.repository;

import com.example.cursovik.entity.Attendance;
import com.example.cursovik.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudent(Student student);
}
