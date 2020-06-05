package com.htc.par.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {	
	
	
	  @Autowired DataSource dataSource;
	  
	  
	  @Bean public PasswordEncoder passwordEncoder() { return new
	  BCryptPasswordEncoder(); }
	  
		@Autowired
		public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder()).usersByUsernameQuery(
					"select user_usr_nm,user_pwd,user_active from user_mstr where user_active = true and user_usr_nm = ? ")
					.authoritiesByUsernameQuery(
							"select um.user_usr_nm, ur.user_role_nm from user_mstr um, user_role ur where um.user_active = true and ur.user_role_id = um.user_role_cd and um.user_usr_nm = ? ")
					.passwordEncoder(new BCryptPasswordEncoder());
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/static/**", "/").permitAll()
			.antMatchers("/area","/userrole","/candidate","/extStaff","/parrole","/skill","/recruiter","prescreener").hasRole("ADMIN")
			.antMatchers("/parentry").hasAnyRole("ADMIN","USER")
					.anyRequest().authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/login")
					.defaultSuccessUrl("/home", true).failureUrl("/login?error=true").permitAll().and().logout()
					.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll();
			http.exceptionHandling().accessDeniedPage("/accessdenied");
			/* .antMatchers("/area") .hasRole("ADMIN") */
		}
	 
	 
}
