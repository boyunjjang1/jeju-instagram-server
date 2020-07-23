package boyunstargram.boyunstargram.user;

import boyunstargram.boyunstargram.user.model.User;

import java.util.List;

public interface UserService {

//    User createUser(User user);


    String createUser(User user);

    User loginUser(String email, String password);

    User updateUser(int user_id, User user);

    void deleteUser(int user_id);

    User getOneUser(int user_id);



}
