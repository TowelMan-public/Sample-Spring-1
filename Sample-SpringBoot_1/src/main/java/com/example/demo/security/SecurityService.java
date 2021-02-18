package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.database.DatabaseMapper;
import com.example.demo.form.LoginForm;


@Service
public class SecurityService implements UserDetailsService {

	@Autowired
	private DatabaseMapper dbMapper;
	
	@Override
	//ログイン処理
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		LoginForm form = dbMapper.getUserByUserName(username);
		if(form == null || form.getUsername() == null) {
			throw new UsernameNotFoundException(username + " is not found");
		}
		
		return new UserDetailsImpl(form);
	}

}