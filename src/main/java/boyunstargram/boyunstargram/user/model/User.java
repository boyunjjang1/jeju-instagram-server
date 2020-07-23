package boyunstargram.boyunstargram.user.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
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
    private String nickname;

    @Column
    private String name;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    public User(){

    }

    public User(int user_id, String email, String password, String nickname, String name,LocalDateTime created_at, LocalDateTime updated_at){
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }



}
