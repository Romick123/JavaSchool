package com.example.cursovik.controller.teacher;

import com.example.cursovik.Security.CustomUserDeatils;
import com.example.cursovik.entity.Attendance;
import com.example.cursovik.entity.Teacher;
import com.example.cursovik.entity.User;
import com.example.cursovik.repository.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    public AttendanceController(AttendanceRepository attendanceRepository,
                                StudentRepository studentRepository,
                                SubjectRepository subjectRepository,
                                TeacherRepository teacherRepository,
                                UserRepository userRepository){
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/new")
    public String form(@AuthenticationPrincipal CustomUserDeatils customUser, Model model){
        User user = customUser.getUser();
        Teacher teacher = teacherRepository.findByUserId(user.getId()).orElseThrow(()-> new IllegalStateException("Не найден профиль учителя"));
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("subjects", teacher.getSubjects());
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
