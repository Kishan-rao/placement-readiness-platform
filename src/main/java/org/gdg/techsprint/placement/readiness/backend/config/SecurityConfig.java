package org.gdg.techsprint.placement.readiness.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // REQUIRED for H2
                .csrf(csrf -> csrf.disable())

                // REQUIRED for H2 iframe
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                // PERMIT THESE
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/h2-console/**",
                                "/auth/**",
                                "/health"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // DO NOT REMOVE
                .httpBasic();

        return http.build();
    }
}
