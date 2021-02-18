package com.example.demo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.form.LoginForm;

public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = -256740067874995659L;
	
	private LoginForm user;
	
	//コンストラクタ
	public UserDetailsImpl(LoginForm account){
		this.user = account;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("USER");
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	//ユーザー名変更
	public void setUsername(String username) {
		user.setUsername(username);
	}
	//アカウントの有効期限の状態を判定
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//アカウントのロック状態を判定
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//資格情報の有効期限の状態を判定
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//有効なユーザーかどうか
	@Override
	public boolean isEnabled() {
		return true;
	}
}
