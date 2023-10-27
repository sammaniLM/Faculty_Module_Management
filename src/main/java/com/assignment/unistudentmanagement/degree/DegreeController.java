package com.assignment.unistudentmanagement.degree;

import com.assignment.unistudentmanagement.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/degree/save")
    public String saveDegree(Degree degree, RedirectAttributes redirectAttributes){
        degreeService.saveDegree(degree);
        redirectAttributes.addFlashAttribute("message", "New Degree Record Updated Successfully");
        return "redirect:/degree";
    }

    @GetMapping("/degree/edit/{degreeid}")
    public String editDegreeRecord(@PathVariable("degreeid") Integer degreeid, Model model, RedirectAttributes redirectAttributes){
        try{
            Degree degree = degreeService.get(degreeid);
            model.addAttribute("degree", degree);
            model.addAttribute("pageTitle", "Update Degree Record (ID : " + degreeid +")");
            model.addAttribute("degreeid", degreeid);
            return "degree_form";
        }catch (DegreeNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/degree";
        }
    }

    @GetMapping("/degree/delete/{degreeid}")
    public String deleteDegreeRecord(@PathVariable("degreeid") Integer degreeid, Model model, RedirectAttributes redirectAttributes){
        try{
            degreeService.deleteDegree(degreeid);
        }catch (DegreeNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/degree";
    }
}
