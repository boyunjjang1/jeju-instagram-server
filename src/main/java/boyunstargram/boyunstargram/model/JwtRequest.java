package boyunstargram.boyunstargram.model;

import lombok.Data;
import lombok.Getter;

@Getter
public class JwtRequest {
    private String email;
    private String password;
}
