package boyunstargram.boyunstargram.service;

import boyunstargram.boyunstargram.model.Role;
import boyunstargram.boyunstargram.model.SecurityUser;
import boyunstargram.boyunstargram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        boyunstargram.boyunstargram.model.User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        if (email.equals("boyumi98@gmail.com")) {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

    public SecurityUser loadUserByEmail(String email) throws UsernameNotFoundException {
        boyunstargram.boyunstargram.model.User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        return new SecurityUser(user.getEmail(), user.getPassword(),user.getUser_id(), user.getNickname(), grantedAuthorities);
    }
}
