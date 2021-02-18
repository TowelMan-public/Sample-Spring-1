package com.example.demo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.database.DatabaseMapper;
import com.example.demo.security.UserDetailsImpl;

@Controller
public class DeleteContlol {
	@Autowired
	DatabaseMapper dbMapper;
	
	@GetMapping("/confirmation_exit")
	public String showDisplayUserDetails() {
		return "/confirmation_exit";
	}
	
	@PostMapping("/confirmation_exit")
	public String showDisplayUserDetails(@AuthenticationPrincipal UserDetailsImpl user) {
		dbMapper.deleteUser(user.getUsername());
		return "/done_exit";
	}
}
