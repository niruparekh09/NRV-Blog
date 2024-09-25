package com.nrv.NrvBlogAPI.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles key management, token creation, user detail extraction,
 * and authority management, facilitating secure authentication and authorization using JWTs.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Component
@Slf4j
public class JwtUtils {

    @Value("${EXP_TIMEOUT}")
    private int jwtExpirationTimeout;

    private Key key;

    @PostConstruct
    public void init() {
        // Use the Keys.secretKeyFor method to generate a key that matches HS512 requirements
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Secure key generation for HS512
    }

    public String generateJwtToken(Authentication authentication) {
        log.info("Generating jwt token {}", authentication);
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationTimeout))
                .claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserNameFromJwtToken(Claims claims) {
        return claims.getSubject();
    }

    public Claims validateJwtToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
        String authorityString = authorities.stream().
                map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        System.out.println(authorityString);
        return authorityString;
    }

    public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
        String authString = (String) claims.get("authorities");
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
        authorities.forEach(System.out::println);
        return authorities;
    }
}
