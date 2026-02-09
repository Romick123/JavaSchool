package com.example.cursovik.controller.teacher;

import com.example.cursovik.entity.Attendance;
import com.example.cursovik.repository.AttendanceRepository;
import com.example.cursovik.repository.StudentRepository;
import com.example.cursovik.repository.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("teacher/attendance")
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public AttendanceController(AttendanceRepository attendanceRepository,
                                StudentRepository studentRepository,
                                SubjectRepository subjectRepository){
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("attendance", new Attendance());
        return "teacher/attendance/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Attendance attendance) {
        attendance.setAttendanceDate(LocalDate.now());
        attendanceRepository.save(attendance);
        return "redirect:/teacher/attendance/new";
    }
}
