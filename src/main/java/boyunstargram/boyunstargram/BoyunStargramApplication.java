package boyunstargram.boyunstargram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestAttributes;
import java.util.Map;

@SpringBootApplication
public class BoyunStargramApplication {


	public static void main(String[] args) {
		SpringApplication.run(BoyunStargramApplication.class, args);
	}

}
