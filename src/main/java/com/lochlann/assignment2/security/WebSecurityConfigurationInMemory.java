package com.lochlann.assignment2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PostMapping;

@Configuration
@Profile("test")
public class WebSecurityConfigurationInMemory {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers("/offices/{id}/changeDepartment").hasAnyRole("ADMIN", "USER")
                .antMatchers("/offices/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/departments/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/offices").permitAll()
                .antMatchers("/departments").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().and().formLogin().and().csrf().disable();
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService user() {
        String encoded = passwordEncoder().encode("secret");
        UserDetails user = User.builder().password(encoded).username("user").roles("USER").build();
        UserDetails admin = User.builder().password(encoded).username("admin").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
