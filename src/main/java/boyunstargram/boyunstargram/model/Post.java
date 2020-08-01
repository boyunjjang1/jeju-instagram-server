package boyunstargram.boyunstargram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int post_id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String content;

    @Column
    private LocalDateTime created;


    public Post() {
        this.created = LocalDateTime.now();
    }

    public Post(int post_id, int user_id, String content, String nickname, LocalDateTime created) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.content = content;
        this.created = created;
        this.nickname = nickname;
    }
}
