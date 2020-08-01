package boyunstargram.boyunstargram.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostResponse {
    private int post_id;
    private int user_id;
    private String nickname;
    private String content;
    private LocalDateTime created;

    public PostResponse() {}

    public PostResponse(Post post) {
        this.user_id = post.getUser_id();
        this.post_id = post.getPost_id();
        this.nickname = post.getNickname();
        this.content = post.getContent();
        this.created = post.getCreated();
    }

}
