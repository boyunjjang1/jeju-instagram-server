package boyunstargram.boyunstargram.user.model;


import boyunstargram.boyunstargram.security.model.AuthUser;
import lombok.*;

import javax.persistence.*;



@Data
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int user_id;


    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    private boolean enable;


    public AuthUser toAuthUser(){
        return new AuthUser(email,password);
    }

    public static class NullUser extends User{
        public NullUser(){
            super(0,"--","--","ROLE_USER",false);
        }
    }
}
