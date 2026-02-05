package com.example.cursovik.controller.admin;

import com.example.cursovik.entity.Grade;
import com.example.cursovik.repository.GradeRepository;
import com.example.cursovik.repository.StudentRepository;
import com.example.cursovik.repository.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/grades")
public class GradeController {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public GradeController(GradeRepository gradeRepository, StudentRepository studentRepository, SubjectRepository subjectRepository){
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("grades", gradeRepository.findAll());
        return "admin/grades/list";
    }

    @GetMapping("/new")
    public String newGradeForm(Model model){
        model.addAttribute("grade", new Grade());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "admin/grades/form";
    }

    @PostMapping("/save")
    public String saveGrade(@ModelAttribute Grade grade) {
        gradeRepository.save(grade);
        return "redirect:/admin/grades";
    }

    @GetMapping("/edit/{id}")
    public String editGrade(@PathVariable Long id, Model model) {
        Grade grade = gradeRepository.findById(id).orElseThrow();

        model.addAttribute("grade", grade);
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "admin/grades/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteGrade(@PathVariable Long id) {
        gradeRepository.deleteById(id);
        return "redirect:/admin/grades";
    }
}
