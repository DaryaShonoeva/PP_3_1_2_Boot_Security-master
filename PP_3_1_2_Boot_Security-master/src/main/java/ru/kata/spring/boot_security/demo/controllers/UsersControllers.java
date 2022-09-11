package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/users")
public class UsersControllers {

    private final UserService usersServices;


    @Autowired
    public UsersControllers(UserService usersServices) {
        this.usersServices = usersServices;
    }


    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("index", usersServices.getUserById(id));
        return "index";
    }
}

