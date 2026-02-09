package com.example.cursovik.controller.parent;

import com.example.cursovik.entity.ClassRoom;
import com.example.cursovik.entity.Student;
import com.example.cursovik.repository.AttendanceRepository;
import com.example.cursovik.repository.ClassRoomRepository;
import com.example.cursovik.repository.GradeRepository;
import com.example.cursovik.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parent/students")
public class ParentStudentController {

    private final ClassRoomRepository classRoomRepository;
    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final GradeRepository gradeRepository;

    public ParentStudentController(ClassRoomRepository classRoomRepository,
                                   StudentRepository studentRepository,
                                   AttendanceRepository attendanceRepository,
                                   GradeRepository gradeRepository){
        this.classRoomRepository = classRoomRepository;
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping
    public String selectClass(Model model){
        model.addAttribute("classes", classRoomRepository.findAll());
        return "parent/classes";
    }

    @GetMapping("/class/{id}")
    public String studentsByClass(@PathVariable Long id, Model model) {
        ClassRoom classRoom = classRoomRepository.findById(id).orElseThrow();
        model.addAttribute("classRoom", classRoom);
        model.addAttribute("students", studentRepository.findByClassRoomId(id));
        return "parent/list";
    }

    @GetMapping("/{id}")
    public String studentProfile(@PathVariable Long id, Model model){
        Student student = studentRepository.findById(id).orElseThrow();
        model.addAttribute("student", student);
        model.addAttribute("attendance", attendanceRepository.findByStudentId(id));
        model.addAttribute("grades", gradeRepository.findByStudentId(id));
        return "parent/profile";
    }
}
