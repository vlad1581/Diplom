package com.example.toDo.securingWeb;

import com.example.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@ComponentScan
public class WebSecurityConfig {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws
            Exception{
        httpSecurity
                .cors().disable()
                .csrf().disable();
        httpSecurity
                .authorizeHttpRequests((request)->request
                        .antMatchers("/home","/about","/home/sign", "/home/registration")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form)->form
                        .loginPage("/home/sign")
                        .permitAll()
                        )
                .logout(LogoutConfigurer::permitAll);
        return httpSecurity.build();
    }

   protected void SecurityFilterChain (AuthenticationManagerBuilder auth) throws Exception{
       auth.userDetailsService(userService)
               .passwordEncoder(passwordEncoder);
   }

}
