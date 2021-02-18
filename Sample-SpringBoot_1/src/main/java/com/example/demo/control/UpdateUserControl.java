package com.example.demo.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.demo.database.DatabaseMapper;
import com.example.demo.form.InsertUserForm;
import com.example.demo.security.UserDetailsImpl;

@Controller
public class UpdateUserControl {
	@Autowired
	DatabaseMapper dbMapper;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/user_detail")
	public String showDisplayUserDetails(@AuthenticationPrincipal UserDetailsImpl user,Model model) {
		model.addAttribute("username",user.getUsername());
		return "/user_detail";
	}
	
	@GetMapping("/update_user")
	public String showDisplayUpdateUser(@AuthenticationPrincipal UserDetailsImpl user,InsertUserForm form, BindingResult bindingResult, Model model) {
		form.setUsername(user.getUsername());
		model.addAttribute("insertUserForm",form);
		return "/update_user";
	}
	
	@PostMapping("/update_user")
	public String updateUser(@AuthenticationPrincipal UserDetailsImpl user,@Valid InsertUserForm form, BindingResult bindingResult, Model model) {
		//入力ﾁｪｯｸ
		if (bindingResult.hasErrors())
			return "/insert_user";
		
		//名前が使われてる
		if(!(dbMapper.isAbleInsertUsername(form.getUsername()) || user.getUsername().equals(form.getUsername()))) {
			model.addAttribute("username_used","既にその名前は使われています。");
			return "/update_user";
		}
		
		form.setPassword(encoder.encode(form.getPassword()));
		dbMapper.updateUser(form.getUsername(), form.getPassword(), user.getUsername());
		user.setUsername(form.getUsername());
		return "redirect:/user_detail";
	}
}
