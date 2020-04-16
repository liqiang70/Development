package com.example.demo.oauth2.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月10日 上午10:48:12
 */

@Slf4j
@Component
public class KiteUserDetailsService implements UserDetailsService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("username is:" + username);
		// 查询数据库操作
		if (!username.equals("admin")) {
			throw new UsernameNotFoundException("the user is not found");
		} else {
			// 用户角色也应在数据库中获取
			String role = "ROLE_ADMIN";
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(role));
			// 线上环境应该通过用户名查询数据库获取加密后的密码
			String password = passwordEncoder.encode("123456");
			log.info("password: " + password);
			return new User(username, password, authorities);
		}
	}

}