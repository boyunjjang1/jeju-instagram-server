package gg.boyun;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gg.boyun.vo.ResponseMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JacksonTests {
    @Test
    public void testObjectMapper(){
        ResponseMessage responseMessage = new ResponseMessage();
        // ResponseMessage -> java class
        responseMessage.setMessage("Hello World");
        responseMessage.setStatus("200");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(responseMessage);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        ObjectMapper
//        String jsonString =
    }

    @Test
    public void testReadJsonString(){
        String jsonString = "{\"status\":\"200\",\"message\":\"Hello World\"}";
        ObjectMapper mapper = new ObjectMapper();
        try {
            ResponseMessage responseMessage = mapper.readValue(jsonString, ResponseMessage.class);
            String status = responseMessage.getStatus();
            System.out.println("statuse : "+status);
            System.out.println(responseMessage.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
