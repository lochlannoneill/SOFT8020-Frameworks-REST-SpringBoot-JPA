package com.lochlann.assignment2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@Profile("test")
public class WebSecurityConfigurationInMemory {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // HOS
                .antMatchers(HttpMethod.POST,"/departments").hasRole("HOS")
                .antMatchers(HttpMethod.PATCH,"/offices/{id}/changeDepartment").hasRole("HOS")
                // HOS and HOD
                .antMatchers(HttpMethod.POST,"/offices").hasAnyRole("HOS", "HOD")
                .antMatchers(HttpMethod.DELETE,"/offices/{id}").hasAnyRole("HOS", "HOD")
                .antMatchers(HttpMethod.PATCH,"/offices/{id}/changeOccupancy").hasAnyRole("HOS", "HOD")
                // others
                .antMatchers("/offices").permitAll()
                .antMatchers("/departments").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().and().formLogin().and().csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
