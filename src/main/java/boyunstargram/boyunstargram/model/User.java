package boyunstargram.boyunstargram.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int user_id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column(nullable = false)
    private String nickname;


}
