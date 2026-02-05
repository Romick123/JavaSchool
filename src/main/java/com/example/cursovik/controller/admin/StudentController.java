package com.example.cursovik.controller.admin;

import com.example.cursovik.entity.Student;
import com.example.cursovik.entity.ClassRoom;
import com.example.cursovik.repository.StudentRepository;
import com.example.cursovik.repository.ClassRoomRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/students")
public class StudentController {
    private final StudentRepository studentRepository;
    private final ClassRoomRepository classRoomRepository;

    public StudentController(StudentRepository studentRepository, ClassRoomRepository classRoomRepository){
        this.classRoomRepository = classRoomRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "admin/students/list";
    }

    @GetMapping("/new")
    public String newStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("classRooms", classRoomRepository.findAll());
        return "admin/students/form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);

        return "redirect:/admin/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow();

        model.addAttribute("student", student);
        model.addAttribute("classRooms", classRoomRepository.findAll());

        return "admin/students/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete student: there are related records"
            );
        }
        return "redirect:/admin/students";
    }
}
