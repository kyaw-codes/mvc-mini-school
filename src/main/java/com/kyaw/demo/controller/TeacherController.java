package com.kyaw.demo.controller;

import com.kyaw.demo.domain.Teacher;
import com.kyaw.demo.service.ClassroomService;
import com.kyaw.demo.service.PositionService;
import com.kyaw.demo.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TeacherController {

    private final TeacherService teacherService;
    private final PositionService positionService;
    private final ClassroomService classroomService;

    public TeacherController(TeacherService teacherService, PositionService positionService, ClassroomService classroomService) {
        this.teacherService = teacherService;
        this.positionService = positionService;
        this.classroomService = classroomService;
    }

    @GetMapping("/admin/teachers")
    public String teachers(Model model) {
        model.addAttribute("teachers", teacherService.listTeachers());
        return "admin/teachers";
    }

    @GetMapping("/admin/registerTeacher")
    public String registerTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("positions", positionService.listPosition());
        model.addAttribute("classrooms", classroomService.listClassroom());
        return "admin/teacherForm";
    }

    @PostMapping("/registerTeacher")
    public String processRegisterTeacher(@Valid Teacher teacher, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("positions", positionService.listPosition());
            model.addAttribute("classrooms", classroomService.listClassroom());
            return "admin/teacherForm";
        }
        teacherService.save(teacher);
        return "redirect:/admin/teachers";
    }

    @GetMapping("/admin/editTeacher/{id}")
    public String editTeacher(@PathVariable int id, Model model) {
        model.addAttribute("teacher", teacherService.findById(id));
        model.addAttribute("positions", positionService.listPosition());
        model.addAttribute("classrooms", classroomService.listClassroom());
        return "admin/teacherEditForm";
    }

    @PostMapping("/editTeacher/{id}")
    public String processEditTeacher(@PathVariable int id, @Valid Teacher teacher, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teacher", teacherService.findById(id));
            model.addAttribute("positions", positionService.listPosition());
            model.addAttribute("classrooms", classroomService.listClassroom());
            return "admin/teacherEditForm";
        }
        teacherService.update(id, teacher);
        return "redirect:/admin/teachers";
    }

    @GetMapping("/admin/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable int id) {
        teacherService.delete(id);
        return "redirect:/admin/teachers";
    }
}
