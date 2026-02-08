package com.example.cursovik.controller.admin;


import com.example.cursovik.entity.Homework;
import com.example.cursovik.repository.ClassRoomRepository;
import com.example.cursovik.repository.HomeworkRepository;
import com.example.cursovik.repository.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/homeworks")
public class HomeworkController {

    private final HomeworkRepository homeworkRepository;
    private final SubjectRepository subjectRepository;
    private final ClassRoomRepository classRoomRepository;

    public HomeworkController(HomeworkRepository homeworkRepository,
                              SubjectRepository subjectRepository,
                              ClassRoomRepository classRoomRepository){
        this.homeworkRepository = homeworkRepository;
        this.subjectRepository = subjectRepository;
        this.classRoomRepository = classRoomRepository;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("homeworks", homeworkRepository.findAll());
        return "admin/homeworks/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Homework homework){
        homeworkRepository.save(homework);
        return "redirect:/admin/homeworks";
    }

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("homework", new Homework());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("classes", classRoomRepository.findAll());
        return "admin/homeworks/form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Homework hw = homeworkRepository.findById(id).orElseThrow();
        model.addAttribute("homework", hw);
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("classes", classRoomRepository.findAll());
        return "admin/homeworks/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        homeworkRepository.deleteById(id);
        return "redirect:/admin/homeworks";
    }
}
