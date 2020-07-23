package boyunstargram.boyunstargram.user;

import boyunstargram.boyunstargram.post.model.Post;
import boyunstargram.boyunstargram.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (this.userRepository.findById(user.getUser_id()).isPresent()) {
            // TODO make custom exception
            throw new RuntimeException("This post already exists: " + user.getUser_id());
        } else {
            return this.userRepository.save(user);
        }
    }

    @Override
    public User updateUser(int user_id, User user) {
        if (this.userRepository.findById(user_id).isPresent()) {
            return this.userRepository.save(user);
        } else {
            // TODO make custom exception
            throw new RuntimeException("Not found post: " + user_id);
        }
    }

    @Override
    public void deleteUser(int user_id) {
        if (this.userRepository.findById(user_id).isPresent()) {
            this.userRepository.deleteById(user_id);
        } else {
            // TODO make custom exception
            throw new RuntimeException("Not found post: " + user_id);
        }
    }

    @Override
    public User getOneUser(int user_id) {

        User user;
        if(this.userRepository.findById(user_id).isPresent()) {
            user = this.userRepository.findById(user_id).get();
        }
        else {
            throw new RuntimeException("Not found post: " + user_id);
        }
        return user;

    }


}
