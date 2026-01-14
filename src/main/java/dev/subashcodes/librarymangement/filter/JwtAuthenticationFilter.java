package dev.subashcodes.librarymangement.filter;

import dev.subashcodes.librarymangement.pojo.Response;
import dev.subashcodes.librarymangement.util.JWTUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtility jwtUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Response responseObj = new Response();
       String authorizationHeader = request.getHeader("Authorization");
       if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
           String jwtToken = authorizationHeader.substring(7);
           boolean isValid = jwtUtility.validateToken(jwtToken);
           if(isValid){
                //proceed with the request
                filterChain.doFilter(request, response);  //
           }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid JWT Token");
           }
       }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid JWT Token");


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/auth/login");// Skip filter for auth endpoints
    }
}
