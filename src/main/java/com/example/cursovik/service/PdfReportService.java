package com.example.cursovik.service;

import com.example.cursovik.entity.Attendance;
import com.example.cursovik.entity.ClassRoom;
import com.example.cursovik.entity.Grade;
import com.example.cursovik.entity.Student;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@Service
public class PdfReportService {
    public byte[] generateClassReport(
            ClassRoom classRoom,
            List<Student> students,
            Map<Long, List<Grade>> gradesMap,
            Map<Long, List<Attendance>> attendanceMap
    ) throws Exception{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);
        document.open();
        Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
        Font textFont = new Font(Font.HELVETICA, 11);

        document.add(new Paragraph("Отчёт об успеваемости и посещаемости\nКласс " + classRoom.getName(), titleFont));
        document.add(Chunk.NEWLINE);
        for (Student student : students) {
            document.add(new Paragraph(
                    "Ученик: " + student.getName(),
                    new Font(Font.HELVETICA, 12, Font.BOLD)
            ));

            List<Grade> grades = gradesMap.get(student.getId());
            List<Attendance> attendance = attendanceMap.get(student.getId());

            if (grades != null) {
                for (Grade g : grades) {
                    boolean present = attendance != null &&
                            attendance.stream().anyMatch(a ->
                                    a.getSubject().equals(g.getSubject()) && a.getPresent());

                    document.add(new Paragraph(
                            g.getSubject().getName() +
                                    " — оценка: " + g.getGrade_value() +
                                    ", присутствие: " + (present ? "был" : "не был"),
                            textFont
                    ));
                }
            }

            document.add(Chunk.NEWLINE);
        }
        document.close();
        return out.toByteArray();
    }
}
