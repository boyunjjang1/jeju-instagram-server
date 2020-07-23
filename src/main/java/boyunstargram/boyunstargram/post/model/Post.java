package boyunstargram.boyunstargram.post.model;

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
    private int id;

//    @Column
//    private String title;

    @Column
    private String contents;

    @Column
    private String imageUrl;

    @Column
    private LocalDateTime created_at;

//    @Column
//    private LocalDateTime updated;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }


    // TODO add user

    public Post() {
    }

    public Post(int id, String contents, String imageUrl, LocalDateTime created_at) {
        this.id = id;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.created_at = created_at;
    }
}
