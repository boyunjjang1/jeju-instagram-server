package gg.boyun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mindrot.jbcrypt.BCrypt;
@RunWith(JUnit4.class)
public class BCryptTests {
    @Test
    public void testEncrypt(){
        String password = "";
        password = "water";

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashedPassword);
    }

    @Test
    public void testComparing(){
        String hashedPassword = "";
        hashedPassword = "$2a$10$dJoraRFBRg3TzMR8IhArned1/VNZ6f37s9Sh2UvPIHTRXUzc3xeiy";
        hashedPassword = "$2a$10$qNy3VGKgML/jlTIqA1iTWOkUkKzonbDIWq68.FQbQrogHID6ZNzqO";

        if(BCrypt.checkpw("water", "$2a$10$dJoraRFBRg3TzMR8IhArned1/VNZ6f37s9Sh2UvPIHTRXUzc3xeiy")){
            System.out.println("비밀번호 일치");
        }else{
            System.out.println("비밀번호 불일치");
        }
    }
}
