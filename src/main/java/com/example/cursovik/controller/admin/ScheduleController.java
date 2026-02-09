package com.example.cursovik.controller.admin;


import com.example.cursovik.DayOfWeek;
import com.example.cursovik.entity.Schedule;
import com.example.cursovik.entity.Student;
import com.example.cursovik.repository.ClassRoomRepository;
import com.example.cursovik.repository.ScheduleRepository;
import com.example.cursovik.repository.SubjectRepository;
import com.example.cursovik.repository.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/schedule")
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;
    private final ClassRoomRepository classRoomRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public ScheduleController(ScheduleRepository scheduleRepository,
                              ClassRoomRepository classRoomRepository,
                              SubjectRepository subjectRepository,
                              TeacherRepository teacherRepository){
        this.scheduleRepository = scheduleRepository;
        this.classRoomRepository = classRoomRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("schedule", scheduleRepository.findAll());
        return "admin/schedule/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("classes", classRoomRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("teachers", teacherRepository.findAll());
        model.addAttribute("days", DayOfWeek.values());
        return "admin/schedule/form";
    }

    @GetMapping("/edit/{id}")
    public String editSchedule(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow();

        model.addAttribute("schedule", schedule);
        model.addAttribute("classes", classRoomRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("teachers", teacherRepository.findAll());
        model.addAttribute("days", DayOfWeek.values());

        return "admin/schedule/form";
    }

    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute Schedule schedule) {
        scheduleRepository.save(schedule);
        return "redirect:/admin/schedule";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id){
        scheduleRepository.deleteById(id);
        return "redirect:/admin/schedule";
    }
}
