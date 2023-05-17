package org.mik.boardGamesCollector.controllers;

import jakarta.validation.Valid;
import org.mik.boardGamesCollector.models.*;
import org.mik.boardGamesCollector.repositories.BoardGameRepository;
import org.mik.boardGamesCollector.repositories.CategoryRepository;
import org.mik.boardGamesCollector.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class BoardGameController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    DesignerController designerController;
    @Autowired
    BoardGameRepository boardGameRepository;

    @GetMapping("/boardgames")
    public String allBoardGames (Model model) {
        model.addAttribute("boardgames", this.boardGameRepository.findAll());
        return "boardgames";
    }

    @GetMapping("/boardgames/edit/{id}")
    public String editBoardGame (Model model, @PathVariable(name = "id") long id) {
        Optional<BoardGame> boardGame = this.boardGameRepository.findById(id);
        if (boardGame.isPresent()) {
            model.addAttribute("boardGame", boardGame.get());
            return "boardGame";
        } else {
            model.addAttribute("Error", "Can't edit this boardgame");
            model.addAttribute("boardgames", this.boardGameRepository.findAll());
            return "boardgames";
        }
    }

    @PostMapping("/boardgames/edit/{id}")
    public String edit (Model model, @PathVariable(name = "id") long id, BoardGame editedBoardGame) {
        this.boardGameRepository.findById(id)
                .map(boardGame -> {
                    boardGame.setTitle(editedBoardGame.getTitle());
                    boardGame.setOwner(editedBoardGame.getOwner());
                    boardGame.setCategories(editedBoardGame.getCategories());
                    boardGame.setCategories(editedBoardGame.getCategories());
                    this.boardGameRepository.save(editedBoardGame);
                    model.addAttribute("Done", "The board game edited.");
                    model.addAttribute("boardgames", this.boardGameRepository.findAll());
                    return "boardgames";
                });
        model.addAttribute("boardgames", this.boardGameRepository.findAll());
        return "boardgames";
    }
    @PostMapping("/boardgames/newBoardGame")
    public String newBoardGame(Model model, @Valid @ModelAttribute BoardGame boardGame) {
        this.boardGameRepository.save(boardGame);
        model.addAttribute("boardgames", this.boardGameRepository.findAll());
        return "boardgames";
    }
}
