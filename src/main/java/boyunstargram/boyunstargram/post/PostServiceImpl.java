package boyunstargram.boyunstargram.post;

import boyunstargram.boyunstargram.post.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getPosts() {
        return this.postRepository.findAll();
    }

    @Override
    public Post createPost(Post post) {
        if (this.postRepository.findById(post.getId()).isPresent()) {
            // TODO make custom exception
            throw new RuntimeException("This post already exists: " + post.getId());
        } else {
            return this.postRepository.save(post);
        }
    }

    @Override
    public Post updatePost(int id, Post post) {
        if (this.postRepository.findById(id).isPresent()) {
            return this.postRepository.save(post);
        } else {
            // TODO make custom exception
            throw new RuntimeException("Not found post: " + id);
        }
    }

    @Override
    public void deletePost(int id) {
        if (this.postRepository.findById(id).isPresent()) {
            this.postRepository.deleteById(id);
        } else {
            // TODO make custom exception
            throw new RuntimeException("Not found post: " + id);
        }
    }

    @Override
    public Post getOnePost(int id) {

        Post post;
        if(this.postRepository.findById(id).isPresent()) {
           post = this.postRepository.findById(id).get();
        }
        else {
            throw new RuntimeException("Not found post: " + id);
        }
        return post;

    }



}
