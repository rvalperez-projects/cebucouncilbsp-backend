package com.cebucouncilbsp.backend.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.cebucouncilbsp.backend.constant.AuthorityCategoryCode;
import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.exception.UserNotFoundException;
import com.cebucouncilbsp.backend.repository.AuthorityRepository;

@Service
public class SecurityUserDetailService implements UserDetailsService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		AuthorityEntity backendUser = authorityRepository.findAuthUserByUsername(username);
		if (null == backendUser) {
			throw new UserNotFoundException();
		}

		return new User(backendUser.getUsername(), backendUser.getPassword(),
				this.getAuthorities(backendUser.getRoleCode()));
	}

	private List<GrantedAuthority> getAuthorities(String roleCode) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(AuthorityCategoryCode.get(roleCode).getRole()));
		return authorities;
	}
}
