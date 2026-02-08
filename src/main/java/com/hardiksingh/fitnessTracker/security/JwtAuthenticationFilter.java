package com.hardiksingh.fitnessTracker.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        if (request.getServletPath().contains("/api/auth")) {
            filterChain.doFilter(request, response);
            System.out.println("This is a public path");
            return;
        }

        System.out.println("Authenticating request");
        final String jwt;

        // 1. Extract the token (removing "Bearer " prefix) && validate it
        try {
            jwt = jwtUtils.getJwtFromHeader(request);
            System.out.printf("TOKEN THERE: " + jwt);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                System.out.print("TOKEN Validated");
                String userId = jwtUtils.getUserIdFromJwtToken(jwt);

                Claims claims = jwtUtils.getAllClaims(jwt);

                List<?> roles = claims.get("roles", List.class);
                System.out.println("ROLES: " + roles);

                List<GrantedAuthority> grantedAuthorities = List.of();

                if (roles != null) {
                    grantedAuthorities = roles.stream()
                            .map(role -> {
                                // 2. Check if the element is a Map (common when using Spring Security objects in JWT)
                                if (role instanceof java.util.Map) {
                                    return (String) ((java.util.Map<?, ?>) role).get("authority");
                                }
                                // 3. Fallback if it is a simple String
                                return role.toString();
                            })
                            .map(SimpleGrantedAuthority::new)
                            .collect(java.util.stream.Collectors.toList());
                }

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userId,
                                null,
                                grantedAuthorities);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
}