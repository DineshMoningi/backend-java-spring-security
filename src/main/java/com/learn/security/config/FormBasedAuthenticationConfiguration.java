package com.learn.security.config;


import com.learn.security.services.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FormBasedAuthenticationConfiguration {

  @Autowired
  private SecurityUserDetailsService securityUserDetailsService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/signin", "/h2-console").permitAll()
        .requestMatchers("/home", "/login", "/register")
        .hasRole("NORMAL")
//        .requestMatchers("/users/**").hasRole("ADMIN")
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
//        .loginPage("/singin")
        ;

    return http.build();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//        .withUser("dinesh")
//        .password(passwordEncoder().encode("dinesh"))
//        .roles("USER");
//    auth.inMemoryAuthentication()
//        .withUser("rithwik")
//        .password(passwordEncoder().encode("rithwik"))
//        .roles("ADMIN");
    auth.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public static BCryptPasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
    return new BCryptPasswordEncoder();
  }

//  @Autowired
//  public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(securityUserDetailsService);
//  }

}
