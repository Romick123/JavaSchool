package com.example.cursovik.repository;

import com.example.cursovik.DayOfWeek;
import com.example.cursovik.entity.ClassRoom;
import com.example.cursovik.entity.Schedule;
import com.example.cursovik.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByClassRoom(ClassRoom classRoom);

    List<Schedule> findByTeacher(Teacher teacher);

    List<Schedule> findByClassRoomAndDayOfWeek(ClassRoom classRoom, DayOfWeek dayOfWeek);
}
