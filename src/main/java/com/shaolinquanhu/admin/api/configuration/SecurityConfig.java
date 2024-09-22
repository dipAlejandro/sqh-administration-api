package com.shaolinquanhu.admin.api.configuration;

import com.shaolinquanhu.admin.api.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author dahl
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login").permitAll() // Permitir acceso al formulario de login
                .requestMatchers(HttpMethod.GET, "/api/v1/alumnos/**").hasAnyRole("USER", "ADMIN") // Permitir GET para USER y ADMIN
                .requestMatchers(HttpMethod.GET, "/api/v1/profesores/**", "/api/v1/distritos/**", "/api/v1/profesores-distritos/**", "/api/v1/usuarios/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/alumnos/**", "/api/v1/profesores/**", "/api/v1/distritos/**", "/api/v1/profesores-distritos/**", "/api/v1/usuarios/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/alumnos/**", "/api/v1/profesores/**", "/api/v1/distritos/**", "/api/v1/profesores-distritos/**", "/api/v1/usuarios/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/alumnos/**", "/api/v1/profesores/**", "/api/v1/distritos/**", "/api/v1/profesores-distritos/**", "/api/v1/usuarios/**").hasRole("ADMIN")
                .anyRequest().denyAll()) // Denegar cualquier otra solicitud
                .formLogin().defaultSuccessUrl("/api/v1/alumnos") // Configuración del formulario de inicio de sesión
                .permitAll()
                .and()
                .httpBasic() // Habilitar autenticación básica
                .and()
                .userDetailsService(userDetailsServiceImpl); // Registrar el UserDetailsService

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
