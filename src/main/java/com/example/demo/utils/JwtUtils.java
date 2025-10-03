package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
/**
 * @author xfff
 * @date 2025-10-03
 * @description Jwt工具类
 */
@Component
public class JwtUtils {

    // 放配置文件更好
    private final String secretKey = "xfff1234567890111111111111111111111111";

    // 1天
    private final long expirationMs = 86400000;

    // 1小时
    private static final long EXPIRATION = 60 * 60 * 1000L;

    // 剩余10分钟自动续期
    private static final long REFRESH_THRESHOLD = 10 * 60 * 1000L;

    public String generateToken(String username, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean shouldRefresh(Date expiration) {
        //剩余过期时间
        long remaining = expiration.getTime() - System.currentTimeMillis();

        //如果剩余时间小于10分钟，则返回true 需要刷新
        return remaining < REFRESH_THRESHOLD;
    }
}
