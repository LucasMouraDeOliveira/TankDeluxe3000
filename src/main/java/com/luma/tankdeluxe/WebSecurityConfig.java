package com.luma.tankdeluxe;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.luma.tankdeluxe.service.UserSecurityService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserSecurityService userDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/", "/index", "/register", "/leaderboard", "/hello").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http
    // .authorizeRequests()
    // .antMatchers("/", "/index", "/register", "/leaderboard", "/assets/**",
    // "/js/**", "/css/**").permitAll()
    // .anyRequest().authenticated()
    // .and()
    // .formLogin()
    // .loginPage("/login")
    // .defaultSuccessUrl("/", true)
    // .permitAll()
    // .and()
    // .logout()
    // .permitAll()
    // .and()
    // .csrf().disable();
    // }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}