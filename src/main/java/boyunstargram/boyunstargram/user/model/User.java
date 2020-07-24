package boyunstargram.boyunstargram.user.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
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

    @Min(8)
    @Max(15)
    @Column
    private String password;


    @Min(4)
    @Max(20)
    @NotBlank(message = "닉네임을 입력해주세요.")
    @Column
    private String nickname;

    @Min(2)
    @Max(20)
    @NotBlank(message = "이름을 입력해주세요.")
    @Column
    private String name;

    @Column
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    public User(){

    }

    public User(int user_id, String email, String password, String nickname, String name,LocalDateTime created_at){
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.created_at = created_at;
//        this.updated_at = updated_at;
    }



}
