package com.example.demo.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my_page")
public class MyPageControl {
	@GetMapping
    public String showDisplay() {
        return "/my_page";
    }
}
