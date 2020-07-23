package boyunstargram.boyunstargram.post;


import boyunstargram.boyunstargram.post.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;


    public PostController(PostService postService) {

        this.postService = postService;
    }

    // TODO add authentication

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts() {
        System.out.println("test");
        List<Post> posts = postService.getPosts();
        return this.postService.getPosts();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post){

        return this.postService.createPost(post);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        // TODO validate id == post.getId()
        return this.postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int id) {
        this.postService.deletePost(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getOnePost(@PathVariable int id) {
        return this.postService.getOnePost(id);
    }

}
