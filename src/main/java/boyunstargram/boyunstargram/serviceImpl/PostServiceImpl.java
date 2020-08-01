package boyunstargram.boyunstargram.serviceImpl;

import boyunstargram.boyunstargram.config.JwtTokenUtil;
import boyunstargram.boyunstargram.exception.ForbiddenException;
import boyunstargram.boyunstargram.exception.ParameterNotFoundException;
import boyunstargram.boyunstargram.model.Post;
import boyunstargram.boyunstargram.model.PostResponse;
import boyunstargram.boyunstargram.model.User;
import boyunstargram.boyunstargram.repository.PostRepository;
import boyunstargram.boyunstargram.repository.UserRepository;
import boyunstargram.boyunstargram.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public PostServiceImpl(PostRepository postRepository, JwtTokenUtil jwtTokenUtil) {
        this.postRepository = postRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public List<Post> getPosts(){
        List<Post> result = this.postRepository.findAll();
        return result;
    }

    @Override
    public void createPost(String token, PostResponse postResponse) {
        int user_id = jwtTokenUtil.getUserIdFromToken(token);
        String nickname = jwtTokenUtil.getUserNicknameFromToken(token);
        Post post = new Post();
        post.setContent(postResponse.getContent());
        post.setUser_id(user_id);
        post.setNickname(nickname);
        this.postRepository.save(post);

    }

    @Override
    public PostResponse updatePost(String token, int post_id, Post post) {
        Post p = this.postRepository.findById(post_id).orElseThrow(() -> new ParameterNotFoundException("Not Found"));

        int user_id = jwtTokenUtil.getUserIdFromToken(token);
        if(user_id == p.getUser_id()) {
            // token에서 가져온 uid랑 일치할 경우
            p.setContent(post.getContent());
            this.postRepository.save(p);


            PostResponse postResponse = new PostResponse(p);
            return postResponse;
        }
        else {
            // 403 error
            throw new ForbiddenException("Forbidden");
        }

    }

    @Override
    public void deletePost(String token, int post_id) {
        int user_id = jwtTokenUtil.getUserIdFromToken(token);
        Post post = this.postRepository.findById(post_id).orElseThrow(() -> new ParameterNotFoundException("Not Found"));
        if (user_id == post.getUser_id()) {
            this.postRepository.deleteById(post_id);
        } else {
            throw new ForbiddenException("Forbidden");
        }
    }

    @Override
    public PostResponse getPost(int post_id) {
        if(this.postRepository.findById(post_id).isPresent()) {
            Post result = this.postRepository.findById(post_id).get();
            PostResponse postResponse = new PostResponse(result);
            return postResponse;
        }
        else {
            throw new ParameterNotFoundException("Not Found");
        }
    }

}
