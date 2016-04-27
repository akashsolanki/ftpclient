/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.samples.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.samples.config.test.UserDetails;
import org.springframework.security.samples.controller.CustomADAuthenticator;
import org.springframework.security.samples.data.UserRoleRepository;
import org.springframework.security.samples.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/*@Value("${ldap.domain}")
    private String DOMAIN;

    @Value("${ldap.url}")
    private String URL;*/
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
	private UserRoleRepository userRoleRepository;
    
    	@Bean
	    public CustomADAuthenticator activeDirectoryLdapAuthenticationProvider() {
    	CustomADAuthenticator provider = new CustomADAuthenticator("ggktech.local", "ldap://172.16.0.18:389");
	        provider.setConvertSubErrorCodesToExceptions(true);
	        provider.setUseAuthenticationRequestCredentials(true);
	        provider.setUserDetailsContextMapper(new UserDetails(userRoleRepository));
	        return provider;
	    }
    	
    	
    	@Bean
	    public DaoAuthenticationProvider authProvider() {
    		DaoAuthenticationProvider authProvider =  new DaoAuthenticationProvider();
    		authProvider.setUserDetailsService(userDetailsService);
    		//authProvider.setPasswordEncoder(encoder());
    		return authProvider;
	    }	  
    	


		@Bean
	    public BCryptPasswordEncoder encoder() {
	        return new BCryptPasswordEncoder(11);
	    }
    	
		@Bean
	    public LoggerListener loggerListener() {
	        return new LoggerListener();
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
	        auth.authenticationProvider(authProvider());
	        auth.eraseCredentials(false);
	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	 http.authorizeRequests().antMatchers("/css/**").permitAll().anyRequest()
             .fullyAuthenticated().and().formLogin();
	    }   
	    
}
