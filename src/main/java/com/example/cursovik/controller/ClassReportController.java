package com.example.cursovik.controller;

import com.example.cursovik.entity.Attendance;
import com.example.cursovik.entity.ClassRoom;
import com.example.cursovik.entity.Grade;
import com.example.cursovik.entity.Student;
import com.example.cursovik.repository.AttendanceRepository;
import com.example.cursovik.repository.ClassRoomRepository;
import com.example.cursovik.repository.GradeRepository;
import com.example.cursovik.repository.StudentRepository;
import com.example.cursovik.service.PdfReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reports")
@PreAuthorize("hasAnyRole('ADMIN','TEACHER','PARENT')")
public class ClassReportController {

    private final ClassRoomRepository classRoomRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final AttendanceRepository attendanceRepository;
    private final PdfReportService pdfReportService;

    public ClassReportController(ClassRoomRepository classRoomRepository,
                                 StudentRepository studentRepository,
                                 GradeRepository gradeRepository,
                                 AttendanceRepository attendanceRepository,
                                 PdfReportService pdfReportService){
        this.classRoomRepository = classRoomRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.attendanceRepository = attendanceRepository;
        this.pdfReportService = pdfReportService;
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<byte[]> generateReport(@PathVariable Long id) throws Exception{
        ClassRoom classRoom = classRoomRepository.findById(id).orElseThrow();
        List<Student> students = studentRepository.findByClassRoomId(id);

        Map<Long, List<Grade>> gradesMap = new HashMap<>();
        Map<Long, List<Attendance>> attendanceMap = new HashMap<>();

        for (Student s : students) {
            gradesMap.put(s.getId(), gradeRepository.findByStudentId(s.getId()));
            attendanceMap.put(s.getId(), attendanceRepository.findByStudentId(s.getId()));
        }

        byte[] pdf = pdfReportService.generateClassReport(
                classRoom, students, gradesMap, attendanceMap
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report_" + classRoom.getName() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
