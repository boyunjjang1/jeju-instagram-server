package boyunstargram.boyunstargram.user;


import boyunstargram.boyunstargram.post.model.Post;
import boyunstargram.boyunstargram.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createUser(@RequestBody User user, BindingResult bindingResult){
        System.out.println("test create User");
        return ResponseEntity.ok(this.userService.createUser(user, bindingResult));
    }

    @PutMapping("{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable int user_id, @RequestBody User user){
        return this.userService.updateUser(user_id, user);
    }

    @DeleteMapping("/{user_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int user_id) {
        this.userService.deleteUser(user_id);
    }

    @GetMapping("/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public User getOneUser(@PathVariable int user_id) {
        return this.userService.getOneUser(user_id);
    }

}
