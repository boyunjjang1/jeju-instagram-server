package boyunstargram.boyunstargram.serviceImpl;

import boyunstargram.boyunstargram.config.JwtTokenUtil;
import boyunstargram.boyunstargram.exception.ResourceConflictException;
import boyunstargram.boyunstargram.model.User;
import boyunstargram.boyunstargram.repository.UserRepository;
import boyunstargram.boyunstargram.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    final PasswordEncoder encode;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encode) {
        this.userRepository = userRepository;
        this.encode = encode;
    }

    @Override
    public User createUser(User user) throws Exception {
        if (this.userRepository.findByEmail(user.getEmail()).isPresent() && this.userRepository.findByNickname(user.getNickname()).isPresent()) {
            throw new ResourceConflictException("Duplicate email and nickname");
        }
        else if(this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResourceConflictException("Duplicate email");
        }
        else if(this.userRepository.findByNickname(user.getNickname()).isPresent()) {
            throw new ResourceConflictException("Duplicate nickname");
        }
        else {
            System.out.println("createUser service Impl check");
            user.setPassword(encode.encode(user.getPassword()));
            return this.userRepository.save(user);
        }
    }

}
