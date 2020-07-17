package gg.boyun.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    final static String KEY = "boyun";

    public static String makeJwtTokenString(Map<String, Object> payloads, Date expirationDate) {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        JwtBuilder jwtBuilder = Jwts.builder()
                //Header 부분 설정
                .setHeader(header)
                //Body  부분 설정
                .setClaims(payloads);
        if (expirationDate != null) {
            jwtBuilder = jwtBuilder.setExpiration(expirationDate);
        }
        String jwtTokenString = jwtBuilder.signWith(SignatureAlgorithm.HS256, KEY.getBytes()).compact();
        return jwtTokenString;
    }

    public static String makeJwtTokenString(Map<String, Object> payloads) {
        return makeJwtTokenString(payloads, null);
    }


    public static Claims getPayloadsFromJwtString(String jwtTokenString) throws ExpiredJwtException, SignatureException, MalformedJwtException {
        Claims claims = Jwts.parser().setSigningKey(KEY.getBytes()).parseClaimsJws(jwtTokenString).getBody();
        return claims;
    }
}
