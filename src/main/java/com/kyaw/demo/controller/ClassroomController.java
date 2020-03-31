package com.kyaw.demo.controller;

import com.kyaw.demo.domain.Classroom;
import com.kyaw.demo.service.ClassroomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping("/admin/classrooms")
    public String classrooms(Model model) {
        model.addAttribute("classrooms", classroomService.listClassroom());
        model.addAttribute("update", model.containsAttribute("update"));
        model.addAttribute("delete", model.containsAttribute("delete"));
        return "admin/classrooms";
    }

    @GetMapping("/admin/registerClassroom")
    public String registerClassroom(Model model) {
        model.addAttribute("classroom", new Classroom());
        return "admin/classroomForm";
    }

    @PostMapping("/registerClassroom")
    public String processClassroomRegistration(@Valid Classroom classroom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/classroomForm";
        }
        classroomService.register(classroom);
        return "redirect:/admin/classrooms";
    }

    @GetMapping("/admin/editClassroom/{id}")
    public String editClassroom(@PathVariable int id, Model model) {
        model.addAttribute("classroom", classroomService.findById(id));
        return "admin/classroomEditForm";
    }

    @PostMapping("/editClassroom/{id}")
    public String processEditClassroom(@PathVariable int id, Classroom classroom, BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/classroomEditForm";
        }
        classroomService.update(id, classroom);
        redirectAttributes.addFlashAttribute("update", true);
        return "redirect:/admin/classrooms";
    }

    @GetMapping("admin/deleteClassroom/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        classroomService.delete(id);
        redirectAttributes.addFlashAttribute("delete", true);
        return "redirect:/admin/classrooms";
    }

}
