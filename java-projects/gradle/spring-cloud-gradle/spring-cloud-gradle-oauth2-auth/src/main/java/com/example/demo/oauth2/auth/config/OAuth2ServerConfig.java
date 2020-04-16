package com.example.demo.oauth2.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月10日 上午10:49:09
 */

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Autowired
	public UserDetailsService kiteUserDetailsService;

	/**
	 * 注入authenticationManager 来支持 password grant type
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("order-client")
				.secret(passwordEncoder.encode("order-secret-8888"))
				.authorizedGrantTypes("refresh_token", "authorization_code", "password")
				.accessTokenValiditySeconds(3600)
				.scopes("all")
				.and()
				.withClient("user-client")
				.secret(passwordEncoder.encode("user-secret-8888"))
				.authorizedGrantTypes("refresh_token", "authorization_code", "password")
				.accessTokenValiditySeconds(3600)
				.scopes("all");
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients();
		security.checkTokenAccess("isAuthenticated()");
		security.tokenKeyAccess("isAuthenticated()");
	}


	@Autowired
	private TokenStore redisTokenStore;

	@Override
	   public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	       /**
	        * redis token 方式
	        */
	       endpoints.authenticationManager(authenticationManager)
	              .userDetailsService(kiteUserDetailsService)
	              .tokenStore(redisTokenStore);
	       endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

	  }

}