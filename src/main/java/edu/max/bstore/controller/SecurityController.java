package edu.max.bstore.controller;

import edu.max.bstore.dto.User;
import edu.max.bstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecurityController {
    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "pages/login";
    }

    @GetMapping(value = "/registration")
    public String registration() {
        return "pages/registration";
    }

    @PostMapping(value = "/registration-user")
    public String registration(
            @RequestParam String username,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String password,
            @RequestParam String phoneNumber
    ) {
        User user = User.builder()
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .build();

        userService.register(user);

        return "redirect:login";
    }
}
