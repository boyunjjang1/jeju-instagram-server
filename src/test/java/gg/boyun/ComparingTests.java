package gg.boyun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ComparingTests {
    @Test
    public void test(){
        String text = "hello World";
        String text2 = new String("hello World");
        text2 = "hello World";

        if(text==text2){
            System.out.println("동일값");
        }else{
            System.out.println("다른값");
        }
    }
    //primitive type
    //byte, short, int, long, float, double, char, boolean


    @Test
    public void testString(){
        String text = "Hello World"; // heap영역에 저장
        text = text+"3"; // 새로운영역에 할당, Hello World3로 저장되긴 함
        String text2 = "Hello World"; // 원래 HelloWorld를 가르킨다
        text = "Hello world2";

    }
}
