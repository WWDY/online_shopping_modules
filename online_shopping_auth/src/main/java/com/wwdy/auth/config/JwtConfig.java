package com.wwdy.auth.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * @author wwdy
 * @date 2022/2/24 17:27
 */
@Configuration
public class JwtConfig {

    private final JwtConfigProperties jwtConfigProperties;

    public JwtConfig(JwtConfigProperties jwtConfigProperties) {
        this.jwtConfigProperties = jwtConfigProperties;
    }

    @Bean
    public JwtBuilder jwtBuilder(){
       return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtConfigProperties.getJwtSecret().getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256);
    }

    @Bean
    public JwtParser jwtParser(){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtConfigProperties.getJwtSecret().getBytes(StandardCharsets.UTF_8)))
                .build();
    }
}
