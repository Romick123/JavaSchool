package com.example.cursovik.controller.admin;

import com.example.cursovik.entity.Teacher;
import com.example.cursovik.entity.User;
import com.example.cursovik.repository.SubjectRepository;
import com.example.cursovik.repository.TeacherRepository;
import com.example.cursovik.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final UserService userService;

    public TeacherController(TeacherRepository teacherRepository, SubjectRepository subjectRepository, UserService userService){
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.userService = userService;
    }

    @GetMapping
    public String listTeachers(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "admin/teachers/list";
    }

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "admin/register";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        model.addAttribute("teacher", teacher);
        model.addAttribute("subjects", subjectRepository.findAll());
        return "admin/teachers/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Teacher teacher,
                       @RequestParam(required = false)List<Long> subjects){
        if (subjects != null){
            teacher.setSubjects(subjectRepository.findAllById(subjects));
        } else {
            teacher.setSubjects(new ArrayList<>());
        }
        teacherRepository.save(teacher);
        return "redirect:/admin/teachers";
    }

    @PostMapping("/teacherRegister")
    public String teacherRegister(@ModelAttribute User user, @ModelAttribute Teacher teacher, @RequestParam(required = false)List<Long> subjects){
        if(subjects != null){
            teacher.setSubjects(subjectRepository.findAllById(subjects));
        } else {
            teacher.setSubjects(new ArrayList<>());
        }
        userService.registerTeacher(user);
        teacher.setUser(user);
        teacherRepository.save(teacher);
        return "redirect:/home";
    }
}
