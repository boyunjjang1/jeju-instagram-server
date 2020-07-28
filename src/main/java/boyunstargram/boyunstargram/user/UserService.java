package boyunstargram.boyunstargram.user;

import boyunstargram.boyunstargram.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }


}
