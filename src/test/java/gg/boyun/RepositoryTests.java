package gg.boyun;

import gg.boyun.entity.UserEntity;
import gg.boyun.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testRepository(){
        /*User user = userRepository.findFirstByIdAndPw("1271572", "boyunjag1");
        System.out.println(user.toString());*/
    }

    @Test
    public void testUserExist(){
        String pw = "boyunjang1";
        UserEntity user = userRepository.findFirstById("127572");
        if(user==null){
            //id가 없는 경우 처리 로직
            System.out.println("ID 없음");
        }else{
            if(user.getPw().equals(pw)){
                //로그인 정보 일치
                System.out.println("정상 정보");
            }else{
                //비밀번호 불일치
                System.out.println("비밀번호 불일치");
            }
        }
    }
}
