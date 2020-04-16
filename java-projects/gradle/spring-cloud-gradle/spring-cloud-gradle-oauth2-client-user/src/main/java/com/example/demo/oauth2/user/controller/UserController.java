package com.example.demo.oauth2.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月9日 下午5:01:52
 */

@Slf4j
@RestController
public class UserController {

	@GetMapping(value = "get")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public Object get(Authentication authentication) {
		authentication.getCredentials();
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
		String token = details.getTokenValue();
		log.info(token);
		return token;
	}
}