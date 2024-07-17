package com.example.quizzzin.controllers.view;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {
    @GetMapping
    public String account(Model model) {
        return "user/account";
    }
}
