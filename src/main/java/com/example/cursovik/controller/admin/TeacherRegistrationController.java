package com.example.cursovik.controller.admin;

import com.example.cursovik.entity.User;
import com.example.cursovik.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherRegistrationController {

    private final UserService userService;

    public TeacherRegistrationController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/admin/teacherreg")
    public String form(Model model){
        model.addAttribute("user", new User());
        return "admin/register";
    }

    @PostMapping("/teacherRegister")
    public String teacherRegister(@ModelAttribute User user){
        userService.registerTeacher(user);
        return "redirect:/home";
    }
}
