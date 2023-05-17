package org.mik.boardGamesCollector.controllers;

import org.mik.boardGamesCollector.models.Designer;
import org.mik.boardGamesCollector.repositories.BoardGameRepository;
import org.mik.boardGamesCollector.repositories.CategoryRepository;
import org.mik.boardGamesCollector.repositories.DesignerRepository;
import org.mik.boardGamesCollector.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class DesignerController {

    @Autowired
    DesignerRepository designerRepository;

    @GetMapping("/designers")
    public String allDesigners(Model model) {
        model.addAttribute("designers", this.designerRepository.findAll());
        return "designers";
    }

    @GetMapping("/designers/edit/{id}")
    public String showEditDesigner (Model model, @PathVariable(value = "id") long id) {
        Optional<Designer> designer = this.designerRepository.findById(id);
        model.addAttribute("designer", designer.get());
        return "designer";
    }

    @PostMapping("/designers/edit/{id}")
    public String editDesigner (Model model, @PathVariable(value = "id") long id, Designer designer) {
        this.designerRepository.findById(id)
                .map(designer1 -> {
                    designer1.setName(designer.getName());
                    designer1.setBoardGames(designer.getBoardGames());
                    this.designerRepository.save(designer1);
                    model.addAttribute("Done", "The designer edited");
                    model.addAttribute("designers", this.designerRepository.findAll());
                    return "designers";
                });
        return "designers";
    }

    @PostMapping("/designers/newDesigner")
    public String newDesigner(Model model, Designer designer) {
        this.designerRepository.save(designer);
        model.addAttribute("Done", "The new designer registered.");
        return "designers";
    }



}
