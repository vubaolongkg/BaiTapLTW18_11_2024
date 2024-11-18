package com.example.demo.configs;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor

@NoArgsConstructor
public class UserInfoUserDetails implements UserDetails{
	private static final long serialVersionUID = 1L;

	private String name;

	private String password;

	private List<GrantedAuthority> authorities;

	public UserInfoUserDetails (UserInfo userInfo) {

	name userInfo.getName();

	password userInfo.getPassword();

	authorities = Arrays.stream(userInfo.getRoles().split(","))

	.map(SimpleGrantedAuthority::new) .collect(Collectors.tolist());

	}

	@Override

	public Collection<? extends GrantedAuthority> getAuthorities() {

	return authorities;

	}

	@Override

	public String getPassword() {

	return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}
