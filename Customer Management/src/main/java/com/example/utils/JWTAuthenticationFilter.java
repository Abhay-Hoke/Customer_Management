package com.example.utils;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter implements Filter {
	
	
	 @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
		 System.out.println("JWTAuthenticationFilter initialized");
	 }


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;

	        String authHeader = httpRequest.getHeader("Authorization");

	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
	            return;
	        }

	        String token = authHeader.substring(7); // Remove "Bearer " prefix

	        try {
	            var claims = JWTUtils.validateToken(token); // Validate token
	            String role = claims.get("role", String.class); // Extract role

	            // Optionally set user info in request attributes
	            httpRequest.setAttribute("username", claims.getSubject());
	            httpRequest.setAttribute("role", role);

	            chain.doFilter(request, response); // Proceed to next filter or servlet
	        } catch (Exception e) {
	            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
	        }
	    }

	    
	    @Override
	    public void destroy() {}
		
	}
	


