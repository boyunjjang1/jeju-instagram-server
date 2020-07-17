package gg.boyun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StringTests {
    @Test
    public void testString(){
        String text = null;
        text = "";

        if((text.isEmpty())||(text==null)) {

        }

        if((text==null)||(text.isEmpty())){
        // 순서 중요 바뀌면 NullPointerException 발생
        }
    }
}
