package boyunstargram.boyunstargram.controller;


import boyunstargram.boyunstargram.model.User;
import boyunstargram.boyunstargram.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encode;

    public UserController(UserService userService, PasswordEncoder encode) {
        this.userService = userService;
        this.encode = encode;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) throws Exception {
        System.out.println("create check");
        return this.userService.createUser(user);
    }

//    @DeleteMapping("")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteUser(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) throws Exception {
//        String tokenValue = token.replace("Bearer ", "").trim();
//        this.userService.deleteUser(tokenValue);
//    }

}
