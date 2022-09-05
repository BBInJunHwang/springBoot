package com.ijhwang.jwt.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ijhwang.jwt.model.UserInfo;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserInfo userInfo;
	
	public PrincipalDetails(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		userInfo.getRoleList().forEach(r->{
			authorities.add(()-> r.toString());
		});
		return authorities;
	}
	
	@Override
	public String getUsername() {
		return userInfo.getUserId();
	}

	@Override
	public String getPassword() {
		return userInfo.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
