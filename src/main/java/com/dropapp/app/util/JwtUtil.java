package com.dropapp.app.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    public static String generateToken(Integer userId) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constant.APP_SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
        Long nowMillis = System.currentTimeMillis();
        Date expirationDate = new Date(nowMillis + Constant.JWT_EXPIRATION_OFFSET);
        return Jwts.builder()
                .setId(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    public static Claims validateToken(String header) throws Exception {
        String token = header.substring(7);
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(Constant.APP_SECRET_KEY))
                .parseClaimsJws(validateToken(token).getId())
                .getBody();
    }

    public static Integer getUserId(String header) throws Exception {
        String token = header.substring(7);

        return Integer.parseInt(validateToken(token).getId());
    }
}
