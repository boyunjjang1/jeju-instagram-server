package boyunstargram.boyunstargram.config;

import boyunstargram.boyunstargram.model.SecurityUser;
import boyunstargram.boyunstargram.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        int user_id = 0;
        String nickname = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.replace("Bearer ", "").trim();
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                System.out.println("email : " + username);
                user_id = jwtTokenUtil.getUserIdFromToken(jwtToken);
                System.out.println("uid : " + user_id);
                nickname = jwtTokenUtil.getUserNicknameFromToken(jwtToken);
                System.out.println("nickname : " + nickname);

            } catch (IllegalArgumentException e) {
                // token이 유효하지 않음
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                // token 만료
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityUser securityUser = this.jwtUserDetailService.loadUserByEmail(username);

            if(jwtTokenUtil.validateToken(jwtToken, securityUser)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(securityUser, null ,securityUser.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }

}
