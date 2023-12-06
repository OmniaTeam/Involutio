package com.omnia.Involutio.service;

import com.omnia.Involutio.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class JWTService {
    public static String ACCESS_TOKEN = "access_token";
    public static long ACCESS_TOKEN_TIME = 24*60*60*1000;
    public static final String REFRESH_TOKEN = "refresh_token";
    private String generateToken(UserEntity user, String token_name, long exp) {
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("token", token_name);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractCustom(String token, String name, Class<T> class_type) {
        return extractClaim(token, claims -> claims.get(name, class_type));
    }

    public Long extractId(String token) {
        return extractCustom(token, "id", Long.class);
    }

    public String extractTokenName(String token) {
        return extractCustom(token, "token", String.class);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode("413F4428472B4B6250655368566D5970337336763979244226452948404D6351");
        return Keys.hmacShaKeyFor(key);
    }

    public boolean isTokenValid(String token) {
        try {
            return extractExpire(token).after(new Date());
        }
        catch (Exception ex){
            return false;
        }

    }

    public Date extractExpire(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Optional<Cookie> getCookieByRequest(HttpServletRequest request, String name) {
        return Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> name.equals(cookie.getName()))
                .findFirst();
    }

    public Cookie createAccessTokenCookie(UserEntity user) {
        return createTokenCookie(ACCESS_TOKEN, user);
    }


    private Cookie createTokenCookie(String name, UserEntity user) {
        String token = generateAccessToken(user);
        Date exp = extractExpire(token);
        Cookie cookie = new Cookie(name, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        //        cookie.setSecure(true);
        cookie.setMaxAge((int) (exp.getTime() - new Date().getTime())/1000);
        return cookie;
    }

    public String generateAccessToken(UserEntity user) {
        return generateToken(user, ACCESS_TOKEN, ACCESS_TOKEN_TIME);
    }
}
