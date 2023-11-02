package com.reachout.backend.config;

import com.reachout.backend.security.CustomUserDetailsService;
import com.reachout.backend.utils.AppConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //get JWT from HTTP request
    //validate token
    //get username from token
    //load user associated with token
    //set spring security

    @Autowired
    private  JwtService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private final HandlerExceptionResolver exceptionResolver;
    @Autowired
    public JwtAuthenticationFilter(HandlerExceptionResolver handlerExceptionResolver) {
        this.exceptionResolver = handlerExceptionResolver;
    }


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        try {
            // Get the requested URL
            String requestUrl = request.getRequestURI();



            if(AppConstants.isWhiteListedURL(requestUrl)) {
                //implement here
                //if requested url is WHITE_LIST_URL then don't validate for Jwt token
                System.out.println("public endpoint...");
                filterChain.doFilter(request, response);
                return;
            }
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("no token found / bearer token not in the header");
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);//extract username from token

            System.out.println("JwtAutheticationFilter: " + username + "\n" + jwt + "\n");

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    System.out.println("token is valid(JwtAuthenticationFilter)!");
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    if (authentication != null && authentication.isAuthenticated()) {
                        UserDetails u = (UserDetails) authentication.getPrincipal();

                        // Print the username (user's identifier)
                        System.out.println("#Username: " + u.getUsername());

                        // Print the user's authorities (roles)
                        System.out.println("#" + "Authorities:");
                        for (GrantedAuthority authority : u.getAuthorities()) {
                            System.out.println(authority.getAuthority());
                        }
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            exceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
