package com.assignment.unistudentmanagement;

import com.assignment.unistudentmanagement.degree.DegreeService;
import com.assignment.unistudentmanagement.module.Module;
import com.assignment.unistudentmanagement.module.ModuleService;
import com.assignment.unistudentmanagement.student.Student;
import com.assignment.unistudentmanagement.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private DegreeService degreeService;

    @Autowired
    private ModuleService moduleService;

    @GetMapping()
    public String showDashBoard(Model model){
        long studentCount = studentService.getStudentCount();
        model.addAttribute("studentCount", studentCount);

        long degreeCount = degreeService.getDegreeCount();
        model.addAttribute("degreeCount", degreeCount);

        long moduleCount = moduleService.getModuleCount();
        model.addAttribute("moduleCount", moduleCount);

        List<Student> latestStudents = studentService.getLatestStudents();
        model.addAttribute("latestStudents", latestStudents);

        List<Module> latestModule = moduleService.getLatestModules();
        model.addAttribute("latestModule", latestModule);

        return "index";
    }
}
