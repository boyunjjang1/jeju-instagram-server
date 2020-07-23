package boyunstargram.boyunstargram.post;

import boyunstargram.boyunstargram.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
//    List<Post> findByAuthor(Long id);
}
