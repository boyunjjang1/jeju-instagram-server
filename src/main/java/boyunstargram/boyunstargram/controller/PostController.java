package boyunstargram.boyunstargram.controller;


import boyunstargram.boyunstargram.model.Post;
import boyunstargram.boyunstargram.model.PostResponse;
import boyunstargram.boyunstargram.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }



    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts() {
        return this.postService.getPosts();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token, @RequestBody PostResponse postResponse) {
        String tokenValue = token.replace("Bearer ", "").trim();
        this.postService.createPost(tokenValue, postResponse);
    }

    @PutMapping("/{post_id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse updatePost(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token, @PathVariable int post_id, @RequestBody Post post) {
        String tokenValue = token.replace("Bearer ", "").trim();
        return this.postService.updatePost(tokenValue, post_id, post);
    }

    @DeleteMapping("/{post_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token, @PathVariable int post_id) {
        String tokenValue = token.replace("Bearer ", "").trim();
        this.postService.deletePost(tokenValue, post_id);
    }

    @GetMapping("/{post_id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPost(@PathVariable int post_id) {
        return this.postService.getPost(post_id);
    }
}
