package boyunstargram.boyunstargram.service;

import boyunstargram.boyunstargram.model.Post;
import boyunstargram.boyunstargram.model.PostResponse;

import java.util.List;

public interface PostService {
    List<Post> getPosts();

    void createPost(String token, PostResponse postResponse);

    PostResponse updatePost(String token, int post_id, Post post);

    void deletePost(String token,int post_id);

    PostResponse getPost(int post_id);

}
