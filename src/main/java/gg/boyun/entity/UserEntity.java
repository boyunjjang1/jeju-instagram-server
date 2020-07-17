package gg.boyun.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id // idx를 말함 pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", columnDefinition = "int(11)")
    private Integer userIdx;

    @Column(name = "id", columnDefinition = "varchar(45)")
    private String id;

    @Column(name = "pw", columnDefinition = "TEXT")
    private String pw;

    @Column(name = "email", columnDefinition = "varchar(100)")
    private String email;
}
