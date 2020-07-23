package boyunstargram.boyunstargram.user;

import boyunstargram.boyunstargram.post.model.Post;
import boyunstargram.boyunstargram.user.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    private static final String SIGNIN_EXCEPTION_MSG = "로그인정보가 일치하지 않습니다.";

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(User user) {
        if (this.userRepository.findById(user.getUser_id()).isPresent()) {
            // TODO make custom exception
            throw new RuntimeException("This post already exists: " + user.getUser_id());
        } else {

            String encodedPassword = this.setupForSave(user);
            user.setPassword(encodedPassword);
            this.userRepository.save(user);

            return "회원가입성공";
        }
    }

    private String setupForSave(User user){
        String password = user.getPassword();
        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return encodedPassword;
    }

    @Override
    public User loginUser(String email, String password){
        User user = userRepository.findByEmail(email);
        Objects.requireNonNull(user, SIGNIN_EXCEPTION_MSG);

        if(!this.isAccordPassword(user, password)){
            throw new IllegalStateException(SIGNIN_EXCEPTION_MSG);
        }

        return user;

    }

    private boolean isAccordPassword(User user, String password){
        String encodedPassword = user.getPassword();
        return BCrypt.checkpw(password, encodedPassword);
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
