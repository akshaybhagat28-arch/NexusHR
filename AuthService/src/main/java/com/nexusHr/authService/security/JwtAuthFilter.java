package com.nexusHr.authService.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nexusHr.authService.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String path = request.getServletPath();
		// PUBLIC APIs SKIP
		if (path.startsWith("/api/auth/") || path.startsWith("/api/payroll/")) {

			filterChain.doFilter(request, response);
			return;
		}

		try {

			String authHeader = request.getHeader("Authorization");

			String token = null;
			String username = null;

			// STEP 1: Check token
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				token = authHeader.substring(7);
				username = jwtService.extractUsername(token);
			}

			// ❌ NO TOKEN → continue request normally (permitAll works)
			if (username == null) {
				filterChain.doFilter(request, response);
				return;
			}

			// STEP 2: If already authenticated
			if (SecurityContextHolder.getContext().getAuthentication() != null) {
				filterChain.doFilter(request, response);
				return;
			}

			// STEP 3: Load user
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			// STEP 4: Validate token
			if (jwtService.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);

			} else {
				sendError(response, 401, "Invalid token");
				return;
			}

			filterChain.doFilter(request, response);

		} catch (Exception e) {

			e.printStackTrace();

			if (!response.isCommitted()) {
				sendError(response, 500, e.toString());
			}
		}
	}

	// ✅ CLEAN RESPONSE METHOD
	private void sendError(HttpServletResponse response, int status, String message) throws IOException {

		response.setStatus(status);
		response.setContentType("application/json");

		response.getWriter().write("{" + "\"status\":" + status + "," + "\"message\":\"" + message + "\"" + "}");
	}
}