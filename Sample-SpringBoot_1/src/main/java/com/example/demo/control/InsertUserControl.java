package com.example.demo.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.database.DatabaseMapper;
import com.example.demo.form.InsertUserForm;

import repository.SecurityRepository;

@Controller
@SessionAttributes(types = InsertUserForm.class)
public class InsertUserControl {
	
	@Autowired
	DatabaseMapper dbMapper;
	
	@Autowired
	PasswordEncoder encoder; 
	
	
	@ModelAttribute("demoForm")
    public InsertUserForm createDemoForm(){
        return new InsertUserForm();
    }
	
	@GetMapping("/insert_user")
	public String showDisplay(InsertUserForm form,BindingResult bindingResult, Model model) {
		return "/insert_user";
	}
	
	@PostMapping("/insert_user")
	public String beforInsertUser(@Valid InsertUserForm form, BindingResult bindingResult, Model model) {
		//入力ﾁｪｯｸ
		if (bindingResult.hasErrors())
			return "/insert_user";
		
		//名前が使われてる
		if(!dbMapper.isAbleInsertUsername(form.getUsername())) {
			model.addAttribute("username_used","既にその名前は使われています。");
			return "/insert_user";
		}
		
		model.addAttribute("username",form.getUsername());
		return "/confirmation_insert_user";
	}
	
	@PostMapping("/confirmation_insert_user")
	public String insertUser(InsertUserForm form, BindingResult bindingResult, Model model) {
		InsertUserForm insetForm = new InsertUserForm();
		insetForm.setUsername(form.getUsername());
		insetForm.setPassword(encoder.encode(form.getPassword()));
		//登録
		dbMapper.insertUser(insetForm);
		
		return "/done_insert_user";
	}
	
	@PostMapping("/done_insert_user")
	public String AfterInsertUser(InsertUserForm form,SessionStatus sessionStatus, BindingResult bindingResult, Model model) {
		model.addAttribute("loginForm",form);
		//セッションオブジェクトを破棄
		sessionStatus.setComplete();
		return "/login";
	}
}
