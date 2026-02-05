package com.example.cursovik.controller.admin;

import com.example.cursovik.entity.Subject;
import com.example.cursovik.repository.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/subjects")
public class SubjectController {

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public String listSubjects(Model model) {
        model.addAttribute("subjects", subjectRepository.findAll());
        return "admin/subjects/list";
    }

    @GetMapping("/new")
    public String newSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "admin/subjects/form";
    }

    @PostMapping("/save")
    public String saveSubject(@ModelAttribute Subject subject) {
        subjectRepository.save(subject);
        return "redirect:/admin/subjects";
    }

    @GetMapping("/edit/{id}")
    public String editSubject(@PathVariable Long id, Model model) {
        Subject subject = subjectRepository.findById(id).orElseThrow();
        model.addAttribute("subject", subject);
        return "admin/subjects/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            subjectRepository.deleteById(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Невозможно удалить предмет: есть зависимые связи");
        }
        return "redirect:/admin/subjects";
    }
}
