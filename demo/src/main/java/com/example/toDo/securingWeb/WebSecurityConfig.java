package com.example.toDo.securingWeb;

import com.example.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserService userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws
            Exception{
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

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("art")
                        .password("123")
                        .roles("Admin")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

   public SecurityFilterChain securityFilterChain(AuthenticationManagerBuilder auth) throws Exception{
       auth.userDetailsService(userService)
               .passwordEncoder(NoOpPasswordEncoder.getInstance());
       return null;
   }

}
