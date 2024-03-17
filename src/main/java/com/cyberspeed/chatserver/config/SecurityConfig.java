package com.cyberspeed.chatserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

// Basic auth will be defaultly enabling if we add sprint security starter
    //  i am creating UserDetails manager and defaulting the username and password

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() throws Exception {

        return new InMemoryUserDetailsManager(
                User.withUsername("test")
                        .password(passwordEncoder().encode("test"))
                        .build());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
