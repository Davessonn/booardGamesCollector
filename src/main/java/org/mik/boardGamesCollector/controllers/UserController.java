package org.mik.boardGamesCollector.controllers;

import jakarta.validation.Valid;
import org.mik.boardGamesCollector.models.User;
import org.mik.boardGamesCollector.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.mik.boardGamesCollector.repositories.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DesignerRepository designerRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BoardGameRepository boardGameRepository;

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", this.userRepository.findAll());
        return "users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser (Model model, @PathVariable(name = "id") long id) {

        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            model.addAttribute("users", user.get());
            return "users";
        } else {
            model.addAttribute("error", "No user with this id");
            model.addAttribute("users", this.userRepository.findAll());
            return "users";
        }
    }
    @PostMapping("/users/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") long id, User editedUser) {
        this.userRepository.findById(id)
                .map(user -> {
                    user.setUserName(editedUser.getUserName());
                    //TODO kiegészíteni ha kell még valami
                    this.userRepository.save(user);
                    model.addAttribute("users", this.userRepository.findAll());
                    return "users";
                });
        model.addAttribute("users", this.userRepository.findAll());
        return "users";
    }

    @PostMapping("/users/newUser")
    public String addNewUser (Model model, @Valid @ModelAttribute User newUser) {
        this.userRepository.save(newUser);
        model.addAttribute("users", this.userRepository.findAll());
        return "users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser (Model model, @PathVariable(name = "id") long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            this.userRepository.deleteById(id);
            model.addAttribute("done", "The user deleted");
        } else {
            model.addAttribute("error", "Can't delete this user.");
        }
        model.addAttribute("users", this.userRepository.findAll());
        return "users";
    }
}
