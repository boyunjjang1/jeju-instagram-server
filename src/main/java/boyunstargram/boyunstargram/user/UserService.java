package boyunstargram.boyunstargram.user;

import boyunstargram.boyunstargram.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import java.util.List;

public interface UserService {


//    int save(UserRequestDto userRequestDto);

    ResponseEntity createUser(User user, BindingResult bindingResult);

    User loginUser(String email, String password);

    User updateUser(int user_id, User user);

    void deleteUser(int user_id);

    User getOneUser(int user_id);



}
