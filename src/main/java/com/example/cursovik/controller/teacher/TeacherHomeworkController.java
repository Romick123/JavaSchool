package com.example.cursovik.controller.teacher;

import com.example.cursovik.entity.Homework;
import com.example.cursovik.entity.Teacher;
import com.example.cursovik.entity.User;
import com.example.cursovik.repository.ClassRoomRepository;
import com.example.cursovik.repository.HomeworkRepository;
import com.example.cursovik.repository.SubjectRepository;
import com.example.cursovik.repository.TeacherRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/teacher/homework")
public class TeacherHomeworkController {

    private final HomeworkRepository homeworkRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final ClassRoomRepository classRoomRepository;

    public TeacherHomeworkController(HomeworkRepository homeworkRepository,
                                     TeacherRepository teacherRepository,
                                     SubjectRepository subjectRepository,
                                     ClassRoomRepository classRoomRepository){
        this.homeworkRepository = homeworkRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.classRoomRepository = classRoomRepository;
    }

    @GetMapping
    public String addForm(@AuthenticationPrincipal User user, Model model) {
        Teacher teacher = teacherRepository.findByUser(user).orElseThrow();
        model.addAttribute("subjects", teacher.getSubjects());
        model.addAttribute("classes", classRoomRepository.findAll());
        model.addAttribute("homework", new Homework());
        return "teacher/homeworks/add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Homework homework) {
        homeworkRepository.save(homework);
        return "redirect:/teacher/homework";
    }
}
