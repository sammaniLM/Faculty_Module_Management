package com.assignment.unistudentmanagement.module;

import com.assignment.unistudentmanagement.degree.Degree;
import com.assignment.unistudentmanagement.degree.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private DegreeService degreeService;

    @GetMapping("/module")
    public String viewModule(Model model){
        List<Module> listModule = moduleService.viewAll();
        model.addAttribute("listModule", listModule);
        return "module";
    }

    @GetMapping("/module/new")
    public String registerNewModule(Model model){
        List<Degree> listDegree = degreeService.viewAll();
        List<String> degreeYears = Arrays.asList("First Year", "Second Year", "Third Year", "Final Year");

        model.addAttribute("listDegree", listDegree);
        model.addAttribute("degreeYears", degreeYears);
        //System.out.println("Degrees : "+ listDegree);
        model.addAttribute("module", new Module());
        model.addAttribute("pageTitle", "Add a New Module");
        return "module_form";
    }

    @PostMapping("/module/save")
    public String saveModule(Module module, RedirectAttributes redirectAttributes){
        moduleService.saveModule(module);
        redirectAttributes.addFlashAttribute("message", "New Module Record Updated Successfully");
        return "redirect:/module";
    }

    @GetMapping("module/edit/{moduleid}")
    public String editModuleRecord(@PathVariable("moduleid") Integer moduleid, Model model, RedirectAttributes redirectAttributes){
        try{
            Module module = moduleService.get(moduleid);
            model.addAttribute("module", module);
            model.addAttribute("pageTitle", "Update Module Record (ID : " + moduleid + ")");
            model.addAttribute("moduleid", moduleid);

            List<Degree> listDegree = degreeService.viewAll();
            model.addAttribute("listDegree", listDegree);

            List<String> degreeYear = Arrays.asList("First Year", "Second Year", "Third Year", "Final Year");
            model.addAttribute("degreeYear", degreeYear);

            return "module_form";
        }catch (ModuleNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/module";
        }
    }

    @GetMapping("/module/delete/{moduleid}")
    public String deleteModuleRecord(@PathVariable("moduleid") Integer moduleid, Model model, RedirectAttributes redirectAttributes){
        try{
            moduleService.deleteModule(moduleid);
        }catch (ModuleNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/module";
    }
}
