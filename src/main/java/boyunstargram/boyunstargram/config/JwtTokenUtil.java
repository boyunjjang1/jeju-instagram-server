package boyunstargram.boyunstargram.config;

import boyunstargram.boyunstargram.model.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    private static final String secret = "boyun_jwt";

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(SecurityUser securityUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", securityUser.getUser_id());
        claims.put("nickname", securityUser.getNickname());
        return doGenerateToken(claims, securityUser);
    }

    private String doGenerateToken(Map<String, Object> claims, SecurityUser securityUser) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(securityUser.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateToken(String token, SecurityUser securityUser) {
        final String username = getUsernameFromToken(token);
        return (username.equals(securityUser.getUsername())) && !isTokenExpired(token);
    }

    public int getUserIdFromToken(String token) throws MalformedJwtException {
        Claims claims = getAllClaimsFromToken(token);
        System.out.println(claims.toString());
        System.out.println(claims.get("user_id"));
        return (int) claims.get("user_id");
    }

    public String getUserNicknameFromToken(String token) throws MalformedJwtException {
        Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("nickname");
    }
}
