package com.assignment.unistudentmanagement.teacher;

import com.assignment.unistudentmanagement.module.Module;
import com.assignment.unistudentmanagement.module.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/teachers")
    public String viewTeacherRecords(Model model){
        List<Teacher> listTeachers = teacherService.viewAll();
        model.addAttribute("listTeachers", listTeachers);
        return "teachers";
    }

    @GetMapping("/teachers/new")
    public String registerNewTeacher(Model model){
        List<Module> listModule = moduleService.viewAll();
        model.addAttribute("listModule", listModule);
        model.addAttribute("teachers", new Teacher());
        model.addAttribute("pageTitle", "Add a New Lecturer");
        return "teacher_form";
    }

    @PostMapping("teachers/save")
    public String saveTeacher(Teacher teacher, RedirectAttributes redirectAttributes) throws TeacherNotFoundException {

        if(teacher.getTeacherid() == null){
            teacher.setUsername(teacher.getUsername());
            teacher.setPassword(teacher.getPassword());
        }else {
            Teacher exsistingTeacher = null;
            try{
                exsistingTeacher = teacherService.get(teacher.getTeacherid());
            }catch (TeacherNotFoundException e){
                throw new RuntimeException(e);
            }
            teacher.setUsername(exsistingTeacher.getUsername());
            teacher.setPassword(exsistingTeacher.getPassword());
        }
        teacherService.saveTeacherRecord(teacher);
        redirectAttributes.addFlashAttribute("message","New Lecturer Record Added Successfully");
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/edit/{teacherid}")
    public String eidtTeacherRecord(@PathVariable("teacherid") Integer teacherid, Model model, RedirectAttributes redirectAttributes){
        try{
            Teacher teachers = teacherService.get(teacherid);
            model.addAttribute("teachers", teachers);
            model.addAttribute("pageTitle", "Update Teacher Record (ID : " + teacherid + ")");
            model.addAttribute("teacherid", teacherid);

            List<Module> listModule = moduleService.viewAll();
            model.addAttribute("listModule", listModule);

            return "teacher_form";
        }catch (TeacherNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/teachers";
        }
    }

    @GetMapping("/teachers/delete/{teacherid}")
    public String deleteTeacherRecord(@PathVariable("teacherid") Integer teacherid, Model model, RedirectAttributes redirectAttributes){
        try{
            teacherService.deleteTeacherRecord(teacherid);
        }catch (TeacherNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/teachers";
    }
}
