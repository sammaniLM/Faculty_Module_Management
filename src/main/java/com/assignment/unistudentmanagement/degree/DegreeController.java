package com.assignment.unistudentmanagement.degree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DegreeController {

    @Autowired
    private DegreeService degreeService;

    @GetMapping("/degree")
    public String viewDegree(Model model){
        List<Degree> listDegree = degreeService.viewAll();
        model.addAttribute("listDegree", listDegree);
        return "degree";
    }

    @GetMapping("/degree/new")
    public String registerNewStudent(Model model){
        model.addAttribute("degree", new Degree());
        model.addAttribute("pageTitle", "Add a New Degree Programme");
        return "degree_form";
    }
}
