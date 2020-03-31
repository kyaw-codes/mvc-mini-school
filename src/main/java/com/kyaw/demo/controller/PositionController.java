package com.kyaw.demo.controller;

import com.kyaw.demo.domain.Position;
import com.kyaw.demo.service.PositionService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Controller
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/admin/positions")
    public String positions(Model model) {
        model.addAttribute("positions", positionService.listPosition());
        model.addAttribute("update", model.containsAttribute("update"));
        model.addAttribute("delete", model.containsAttribute("delete"));
        return "admin/positions";
    }

    @GetMapping("/admin/registerPosition")
    public String registerPosition(Model model) {
        model.addAttribute("position", new Position());
        return "admin/positionForm";
    }

    @PostMapping("/registerPosition")
    public String processPositionRegistration(@Valid Position position, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "admin/positionForm";
        positionService.register(position);
        return "redirect:/admin/positions";
    }

    @GetMapping("/admin/editPosition/{id}")
    public String editPosition(@PathVariable int id, Model model) {
        model.addAttribute("position", positionService.findById(id));
        return "admin/positionEditForm";
    }

    @PostMapping("/editPosition/{id}")
    public String processEditPosition(@PathVariable int id, @Valid Position position, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) return "admin/positionEditForm";
        positionService.update(id, position);
        redirectAttributes.addFlashAttribute("update", true);
        return "redirect:/admin/positions";
    }

    @GetMapping("/admin/deletePosition/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        positionService.delete(id);
        redirectAttributes.addFlashAttribute("delete", true);
        return "redirect:/admin/positions";
    }
}
