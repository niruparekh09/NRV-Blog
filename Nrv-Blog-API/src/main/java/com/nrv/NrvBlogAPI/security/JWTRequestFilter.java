package com.nrv.NrvBlogAPI.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * This class intercepts incoming HTTP requests, extracts and validates the JWT token from the
 * Authorization header. This allows Spring Security to recognize the authenticated
 * user and enforce authorization rules throughout the application. If the request does not
 * contain a valid Bearer token, it simply continues without setting any authentication.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            System.out.println("got bearer token");
            String token = authHeader.substring(7);
            Claims claims = jwtUtils.validateJwtToken(token);
            String email = jwtUtils.getUserNameFromJwtToken(claims);
            List<GrantedAuthority> authorities = jwtUtils.getAuthoritiesFromClaims(claims);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(email, null,
                            authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } else
            System.out.println("req did not contain any bearer token");
        filterChain.doFilter(request, response);
    }
}

