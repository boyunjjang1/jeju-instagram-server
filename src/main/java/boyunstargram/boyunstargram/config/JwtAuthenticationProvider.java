package boyunstargram.boyunstargram.config;

import boyunstargram.boyunstargram.model.User;
import boyunstargram.boyunstargram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        if(!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("UnAuthorized");
        }

        return new UsernamePasswordAuthenticationToken(email, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
