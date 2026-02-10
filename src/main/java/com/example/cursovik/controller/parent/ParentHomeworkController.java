package com.example.cursovik.controller.parent;


import com.example.cursovik.repository.ClassRoomRepository;
import com.example.cursovik.repository.HomeworkRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parent/homework")
public class ParentHomeworkController {

    private final HomeworkRepository homeworkRepository;
    private final ClassRoomRepository classRoomRepository;

    public ParentHomeworkController(HomeworkRepository homeworkRepository, ClassRoomRepository classRoomRepository) {
        this.homeworkRepository = homeworkRepository;
        this.classRoomRepository = classRoomRepository;
    }

    @GetMapping
    public String selectClass(Model model) {
        model.addAttribute("classes", classRoomRepository.findAll());
        return "parent/student/classes";
    }

    @GetMapping("/class/{id}")
    public String view(
            @PathVariable Long id,
            Model model) {

        model.addAttribute("homeworks", homeworkRepository.findByClassRoomId(id));

        return "parent/homework/list";
    }
}
