package boyunstargram.boyunstargram.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@NoArgsConstructor
public class SecurityUser implements UserDetails {

    private String email;
    private String password;
    private int user_id;
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUser(String email, String password, int user_id, String nickname, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.user_id = user_id;
        this.nickname = nickname;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getNickname(){
        return this.nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
