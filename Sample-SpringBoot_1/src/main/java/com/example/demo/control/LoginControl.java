package com.example.demo.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginForm;

@Controller
@RequestMapping("/login")
public class LoginControl {
	//ログイン処理
	@GetMapping
    public String showDisplay(LoginForm form) {
        return "/login";
    }
	
	//ログイン処理
	@PostMapping
    public String showDisplay(Model model) {
        return "/login";
    }
}