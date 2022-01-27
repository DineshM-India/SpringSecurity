package com.dinesh.SpSecureApp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
@Autowired
DataSource datasource;
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception{
PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
auth.jdbcAuthentication() 
.dataSource(datasource) //Call the DB
.withDefaultSchema()    //Tables Create Automatically
.withUser("dinesh")   //Create User 
.password(encoder.encode("123a")) //Create password with encoded
.roles("USERS") //Role for level of acces
.and()
.withUser("admin").password(encoder.encode("admin")).roles("ADMIN");
}

// Role Based Auhtorization
@Override
protected void configure(HttpSecurity http) throws Exception {
http.csrf().disable().authorizeRequests()
.antMatchers("/admin")
.hasRole("ADMIN") // Access Admin
.antMatchers("/user")
.hasAnyRole("ADMIN","USERS") // Access User and Admin
.antMatchers("/").permitAll() // Access anyone
.and().formLogin().and().logout().logoutUrl("/logout").invalidateHttpSession(true);
}


}
