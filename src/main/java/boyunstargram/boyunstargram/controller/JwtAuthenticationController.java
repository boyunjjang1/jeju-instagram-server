package boyunstargram.boyunstargram.controller;

import boyunstargram.boyunstargram.config.JwtAuthenticationProvider;
import boyunstargram.boyunstargram.config.JwtTokenUtil;
import boyunstargram.boyunstargram.model.JwtRequest;
import boyunstargram.boyunstargram.model.JwtResponse;
import boyunstargram.boyunstargram.model.SecurityUser;
import boyunstargram.boyunstargram.model.User;
import boyunstargram.boyunstargram.repository.UserRepository;
import boyunstargram.boyunstargram.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtAuthenticationController {

    @Autowired private JwtAuthenticationProvider authenticationProvider;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private SecurityUser securityUser;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final SecurityUser securityUser = userDetailService.loadUserByEmail(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(securityUser);
        final String email = userDetailService.loadUserByUsername(authenticationRequest.getEmail()).getUsername();

        final String nickname = securityUser.getNickname();

        return ResponseEntity.ok(new JwtResponse(token,email,nickname));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationProvider.setEncoder(passwordEncoder);
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
