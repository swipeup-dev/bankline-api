package com.swipeupdev.banklineapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swipeupdev.banklineapi.model.security.UserSecurity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private UserSecurity userSecurity;

    static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    private final String HEADER = "Authorization";

    static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (checkJWTToken(request, response)) {
                Claims claims = validateToken(request);
                if (claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {

            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            Map<String, Object> errorDetails = new HashMap<>();

            errorDetails.put("status", response.getStatus());
            errorDetails.put("message", e.getMessage());
            errorDetails.put("error", e.getClass().getSimpleName());
            errorDetails.put("timestamp", LocalDateTime.now().toString());
            errorDetails.put("path", request.getRequestURI());

            MAPPER.writeValue(response.getWriter(), errorDetails);

            return;
        }
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(userSecurity.getPrefix(), "");
        JwtParser parser = Jwts.parser().setSigningKey(userSecurity.getKey().getBytes());
        Jws<Claims> claimsJws;
        try {
            claimsJws = parser.parseClaimsJws(jwtToken);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            LOGGER.warn("Error parsing token: " + e.toString());
            throw e;
        }
        return claimsJws.getBody();
    }

    private void setUpSpringAuthentication(Claims claims) {
        List<String> authorities = (List<String>) claims.get("authorities");
        authorities.forEach(System.err::println);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            claims.getSubject(),
            null,
            authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(userSecurity.getPrefix()))
            return false;
        return true;
    }
}
