package com.example.cursovik.controller.admin;

import com.example.cursovik.entity.ClassRoom;
import com.example.cursovik.repository.ClassRoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/classes")
public class ClassRoomController {
    private final ClassRoomRepository classRoomRepository;

    public ClassRoomController(ClassRoomRepository classRoomRepository) {
        this.classRoomRepository = classRoomRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("classes", classRoomRepository.findAll());
        return "admin/classes/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("classRoom", new ClassRoom());
        return "admin/classes/form";
    }

    @PostMapping
    public String save(@ModelAttribute ClassRoom classRoom) {
        classRoomRepository.save(classRoom);
        return "redirect:/admin/classes";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("classRoom", classRoomRepository.findById(id).orElseThrow());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "admin/classes/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            classRoomRepository.deleteById(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Невозможно удалить предмет: есть зависимые связи");
        }
        return "redirect:/admin/classes";
    }
}
