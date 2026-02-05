package com.example.cursovik.controller.admin;

import com.example.cursovik.entity.Teacher;
import com.example.cursovik.repository.SubjectRepository;
import com.example.cursovik.repository.TeacherRepository;
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

    public TeacherController(TeacherRepository teacherRepository, SubjectRepository subjectRepository){
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public String listTeachers(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "admin/teachers/list";
    }

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "admin/teachers/form";
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
}
