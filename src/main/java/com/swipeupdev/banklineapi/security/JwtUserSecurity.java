package com.swipeupdev.banklineapi.security;

import com.swipeupdev.banklineapi.model.dto.SessaoDto;
import com.swipeupdev.banklineapi.model.exception.InvalidAuthenticationException;
import com.swipeupdev.banklineapi.model.security.UserSecurity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUserSecurity implements UserSecurity {

    public static final String PREFIX = "Bearer ";
    private static final String ROLE = "ROLE_USER";
    private StringBuilder strBuilder = new StringBuilder();

    @Value("${jwt.secret}")
    private String key;

    @Value("${jwt.expiration}")
    private long expiration;

    public String getKey() {
        return key;
    }

    public long getExpiration() {
        return expiration;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public void validateAuthenticantion(String login) {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isString = obj instanceof String;
        if (!(isString) || !(login.equals((String) obj))) {
            throw new InvalidAuthenticationException("Usuário autenticado não corresponde ao login fornecido.");
        }
    }

    @Override
    public String generateToken(SessaoDto session) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(ROLE);

        strBuilder.setLength(0);
        strBuilder.append(PREFIX);
        strBuilder.append(Jwts
            .builder()
            .setSubject(session.getLogin())
            .claim(
                "authorities",
                grantedAuthorities
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList())
                )
            .setIssuedAt(session.getDataInicio())
            .setExpiration(session.getDataFim())
            .signWith(SignatureAlgorithm.HS512, key.getBytes())
            .compact()
        );

        return strBuilder.toString();
    }
}
