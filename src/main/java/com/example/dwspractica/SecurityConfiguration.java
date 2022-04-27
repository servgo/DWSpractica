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

        auth.inMemoryAuthentication().withUser("user").password(encoder.encode("pass")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("adminpass")).roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Public pages
        http.authorizeRequests().antMatchers("/home/").permitAll();
        http.authorizeRequests().antMatchers("/home/login").permitAll();
        http.authorizeRequests().antMatchers("/home/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/home/logout").permitAll();
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().antMatchers("/allGames").permitAll();
        http.authorizeRequests().antMatchers("/gameDetails/**").permitAll();
        http.authorizeRequests().antMatchers("/game/**/ratings").permitAll();
        http.authorizeRequests().antMatchers("/filteredGames").permitAll();

        // Private pages (all other pages)
        http.authorizeRequests().antMatchers("/addGame").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/addedGame").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/deleted/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/update/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/updated/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/game/**/ratings/createRating").hasRole("USER");
        http.authorizeRequests().antMatchers("/game/**/newRating").hasRole("USER");
        http.authorizeRequests().antMatchers("/game/**/deleteRating/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/addedToCart/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/shoppingCart").hasRole("USER");
        http.authorizeRequests().antMatchers("/RemoveGame/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/RemoveShoppingCart").hasRole("USER");
        http.authorizeRequests().antMatchers("/MadeOrder").hasRole("USER");
        http.authorizeRequests().antMatchers("/showOrders").hasRole("USER");
        http.authorizeRequests().antMatchers("/deleteOrder/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/myProfile").hasAnyRole("USER", "ADMIN");

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
