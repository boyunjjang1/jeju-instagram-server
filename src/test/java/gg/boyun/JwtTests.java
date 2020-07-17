package gg.boyun;

import gg.boyun.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)

public class JwtTests {
    @Test
    public void testJwtToken(){
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .setSubject("user")
                .claim("idx", 2)
//                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*1)*60*24*30))
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*1)*60*24*30))
                .signWith(SignatureAlgorithm.HS256, "samdasoo".getBytes())
                .compact(); // 해야 string나옴

        System.out.println(jwt);
    }

    @Test
    public void testJwtTokenCreation(){
        Date expirationDate = new Date(System.currentTimeMillis()+4*1000*60);
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("idx", 2);
        String jwtString = JwtUtil.makeJwtTokenString(payloads, expirationDate);
        System.out.println(jwtString);
    }

    @Test
    public void testReadJwt(){
        String jwt = "";
        jwt = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVlc2FnZSI6IkhlbGxvIFdvcmxkIiwiZXhwIjoxNTkxODU3NjM5fQ.QshmjMHhOL0Pf4Qq-CANJepVkdpSvDNPp8fHzVGBjPU";
        jwt = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVlc2FnZSI6IkhlbGxvIFdvcmxkIiwiZXhwIjoxNTkxOTAxMjM5fQ.VuD6DRwSJI3WSAVekwVPbqeYsb5aYHoXLdjXgwbb9Jg";
        jwt ="eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVzc2FnZSI6IkhlbGxvIFdvcmxkIiwiZXhwIjoxNTkxOTAxNzcxfQ.gouFzA3DSPIEcDEWzzm3FwdxbbyjDQYquQjscMT5RFU";
        Claims claims = Jwts.parser()
                .setSigningKey("samdasoo".getBytes())
                .requireSubject("user")
                .parseClaimsJws(jwt)
                .getBody();

        System.out.println(claims.toString());
    }


}
