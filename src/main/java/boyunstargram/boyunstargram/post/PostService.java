package boyunstargram.boyunstargram.post;

import boyunstargram.boyunstargram.post.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    List<Post> getPosts();

    Post createPost(Post post);

    Post updatePost(int id, Post post);

    void deletePost(int id);

    Post getOnePost(int id);
}
