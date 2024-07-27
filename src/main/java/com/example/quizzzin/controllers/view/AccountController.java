package com.example.quizzzin.controllers.view;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class AccountController {
    @GetMapping("/settings/account")
    public String account(Model model) {
        return "user/account";
    }

    @GetMapping("profile/{id}")
    public String profile(@PathVariable Long id, Model model) {
        return "user/profile";
    }
}
