package com.assignment.unistudentmanagement.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/students")
    public String viewStudents(Model model){
        List<Student> listStudent = studentService.viewAll();
        model.addAttribute("listStudent", listStudent);
        return "students";
    }

    @GetMapping("/students/new")
    public String registerNewStudent(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Register New User");
        return "student_form";
    }

    @PostMapping("/students/save")
    public String saveStudent(Student student, RedirectAttributes redirectAttributes){

        if(student.getStudentid() == null){
            student.setEmail(student.getEmail());
        }else{
            Student exsistingStudent = null;
            try {
                exsistingStudent = studentService.get(student.getStudentid());
            } catch (StudentNotFoundException e) {
                throw new RuntimeException(e);
            }
            student.setEmail(exsistingStudent.getEmail());
        }

        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("message", "New Student Record Added Successfully");
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{studentid}")
    public String editStudentRecord(@PathVariable("studentid") Integer studentid, Model model, RedirectAttributes redirectAttributes){
        try {
           Student student =  studentService.get(studentid);
           model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Update Student Record (ID : " + studentid +")");
            model.addAttribute("student_id", studentid);
            return "student_form";
        } catch (StudentNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/students";
        }
    }

    @GetMapping("/students/delete/{studentid}")
    public String deleteStudentRecord(@PathVariable("studentid") Integer studentid, Model model, RedirectAttributes redirectAttributes){
        try {
            studentService.deleteStudent(studentid);
        } catch (StudentNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/students";
    }
}
