package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admins")
public class AdminControllers {
    private final UserService usersServices;


    @Autowired
    public AdminControllers(UserService usersServices) {
        this.usersServices = usersServices;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", usersServices.listUser());
        return "users";
    }

    @GetMapping("/new")
    public String createNewUserForm(Model model) {                  // по запросу "/new" в браузер вернется форма для создания нового юзера
        model.addAttribute("user", new User());
        return "new";                                         // возвращаем название Thymeleaf-шаблона, где у нас будет лежать форма для создания нового юзера
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user){
        usersServices.addUser(user);
        return "redirect:/users";                                // указываем адрес, на который мы хотим перенаправить пользоватея
    }

    @GetMapping("/{id}/edit")
    public String editUsers(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", usersServices.getUserById(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String updateUsers(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        usersServices.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUsers(@PathVariable("id") Long id) {
        usersServices.removeUser(id);
        return "redirect:/users";
    }
}
