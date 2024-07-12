package com.example.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //리소스 접근 설정
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login","/loginProc", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("USER")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );
        //폼 로그인 설정
        http
                .formLogin(auth -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll());
        //csrf 설정(개발 모드에서만)
        http
                .csrf(AbstractHttpConfigurer::disable);

        //다중 로그인 설정
        http
            .sessionManagement(auth -> auth
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true));

        //세션 고정 보호
        http
            .sessionManagement(auth -> auth
                .sessionFixation().changeSessionId());

        return http.build();
    }
}
