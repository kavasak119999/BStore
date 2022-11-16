package edu.max.bstore.controller;

import edu.max.bstore.dto.User;
import edu.max.bstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@Controller
@RequestMapping(value = "/auth")
public class SecurityController {
    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "/pages/auth/lg";
    }

    @PostMapping(value = "/login-user")
    public String login(@RequestParam(value = "username") String id,
                        @RequestParam(value = "password") String pass) throws LoginException {
        userService.checkCredentials(id,pass);
        return "redirect://";
    }

    @GetMapping(value = "/registration")
    public String registration() {
        return "/pages/auth/registration";
    }

    @PostMapping(value = "/registration-user")
    public String registration(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String phone_number
    ) {
        User user = User.builder()
                .username(username)
                .password(password)
                .phoneNumber(phone_number)
                .build();

        userService.register(user);

        return "redirect:/";
    }
}
