package com.example.cursovik.repository;

import com.example.cursovik.entity.ClassRoom;
import com.example.cursovik.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByClassRoom(ClassRoom classRoom);
}
