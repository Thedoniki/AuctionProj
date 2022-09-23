package com.example.auctionp;


import com.example.auctionp.Models.User;
import com.example.auctionp.Services.S_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource dS;

    @Autowired
    S_User uService;


    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).dataSource(dS)
                .usersByUsernameQuery("SELECT username, password, enabled FROM User WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, role FROM User WHERE username=?");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests().antMatchers("/login", "/css/**", "/js/**", "/img/**").permitAll()
                .and().authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/1").permitAll()
                .and().logout().invalidateHttpSession(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
    }

    public User getLoggedInUser(){
        String uname;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            uname = ((UserDetails)principal).getUsername();
            System.out.println(uService.findUserByUsername(uname));
            return uService.findUserByUsername(uname);
        }
        return null;
    }
}