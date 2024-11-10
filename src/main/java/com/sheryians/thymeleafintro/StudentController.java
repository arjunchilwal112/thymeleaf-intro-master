package com.sheryians.thymeleafintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping({"/", "/home"})
    public String getAllStudents(Model model) {
        model.addAttribute("allStudents", studentService.getAllStudents());
        return "homepage";
    }
    @GetMapping("/delete/{rollno}")
    public String deleteStudent(@PathVariable int rollno) {
        studentService.removeStudent(rollno);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addStudentView() {
        return "addstudent";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute(name = "student") Student student) {
        studentService.addStudent(student);
        return "redirect:/";
    }

    @GetMapping("/update/{rollno}")
    public String updateStudentView(@PathVariable int rollno, Model model) {
        model.addAttribute("student", studentService.getStudent(rollno));
        return "updatestudent";
    }
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute(name = "student") Student student) {
        studentService.updateStudent(student, student.getRollNo());
        return "redirect:/";
    }
}
