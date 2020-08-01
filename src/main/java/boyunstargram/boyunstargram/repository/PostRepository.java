package boyunstargram.boyunstargram.repository;

import boyunstargram.boyunstargram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findById(int post_id);

    @Transactional
    void deleteById(int post_id);


}
