package com.nexusHr.authService.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nexusHr.authService.repository.CustomAccessDeniedHandler;
import com.nexusHr.authService.security.JwtAuthFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http

				// ENABLE CORS
				.cors(cors -> {
				})

				// DISABLE CSRF
				.csrf(csrf -> csrf.disable())

				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.authorizeHttpRequests(auth -> auth

						.requestMatchers("/api/auth/**", "/api/department/**", "/api/employee/**", "/api/attendance/**",
								"/api/session/**", "/api/leaves/**", "/api/reviews/**", "/api/payroll/**")
						.permitAll()

						.anyRequest().authenticated())

				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

				.exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler))

				.build();
	}

	// CORS CONFIG
	@Bean
	 CorsConfigurationSource corsConfigurationSource() {

	    CorsConfiguration configuration = new CorsConfiguration();

	    configuration.setAllowedOrigins(List.of(
	        "http://localhost:5173",
	        "https://nexus-hr-project-oaqh7c02-akshaybhagat28-1992s-projects.vercel.app"
	    ));

	    configuration.setAllowedMethods(List.of("*"));
	    configuration.setAllowedHeaders(List.of("*"));
	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source =
	            new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}


	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {

		return config.getAuthenticationManager();
	}
}