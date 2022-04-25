package com.example.dwspractica;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String encodedPassword = encoder.encode("pass");

        auth.inMemoryAuthentication().withUser("user").password(encodedPassword).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Public pages
        http.authorizeRequests().antMatchers("/home/").permitAll();
        http.authorizeRequests().antMatchers("/home/login").permitAll();
        http.authorizeRequests().antMatchers("/home/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/home/logout").permitAll();
        http.authorizeRequests().antMatchers("/css/**").permitAll();

        // Private pages (all other pages)
        http.authorizeRequests().anyRequest().authenticated();

        // Login form
        http.formLogin().loginPage("/home/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/home/");
        http.formLogin().failureUrl("/home/loginerror");

        // Logout
        http.logout().logoutUrl("/home/logout");
        http.logout().logoutSuccessUrl("/home/");

        // Disable CSRF at the moment
        http.csrf().disable();
    }
}
