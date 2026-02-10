package com.example.cursovik.controller.teacher;

import com.example.cursovik.entity.*;
import com.example.cursovik.repository.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/teacher/grades")
public class TeacherGradesController {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final ClassRoomRepository classRoomRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public TeacherGradesController(TeacherRepository teacherRepository,
                                   SubjectRepository subjectRepository,
                                   ClassRoomRepository classRoomRepository,
                                   StudentRepository studentRepository,
                                   GradeRepository gradeRepository){
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.classRoomRepository = classRoomRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping
    public String selectSubject(@AuthenticationPrincipal User user, Model model){
        Teacher teacher = teacherRepository.findByUser(user).orElseThrow();
        model.addAttribute("subjects", teacher.getSubjects());
        return "teacher/grades/subjects";
    }

    @GetMapping("/subject/{id}")
    public String selectClass(@PathVariable Long id, Model model){
        Subject subject = subjectRepository.findById(id).orElseThrow();
        model.addAttribute("subject", subject);
        model.addAttribute("classes", classRoomRepository.findAll());
        return "teacher/grades/classes";
    }

    @GetMapping("/subject/{subjectId}/class/{classId}")
    public String students(@PathVariable Long subjectId, @PathVariable Long classId, Model model) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        ClassRoom classRoom = classRoomRepository.findById(classId).orElseThrow();
        model.addAttribute("subject", subject);
        model.addAttribute("classRoom", classRoom);
        model.addAttribute("students", studentRepository.findByClassRoomId(classId));
        return "teacher/grades/students";
    }

    @PostMapping("/add")
    public String addGrade(@RequestParam Long studentId, @RequestParam Long subjectId, @RequestParam Integer gradeValue) {

        Grade grade = new Grade();
        grade.setStudent(studentRepository.findById(studentId).orElseThrow());
        grade.setSubject(subjectRepository.findById(subjectId).orElseThrow());
        grade.setGrade_value(gradeValue);
        grade.setDate(LocalDate.now());
        gradeRepository.save(grade);
        return "redirect:/teacher/grades/subject/" + subjectId + "/class/" + grade.getStudent().getClassRoom().getId();
    }
}
