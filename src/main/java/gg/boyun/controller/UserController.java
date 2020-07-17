package gg.boyun.controller;


import com.google.gson.JsonObject;
import gg.boyun.entity.UserEntity;
import gg.boyun.repository.UserRepository;
import gg.boyun.util.JsonUtil;
import gg.boyun.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String userInsertion(@RequestHeader Map<String, String> headerMap, @RequestBody Map<String, Object> bodyMap){
        JsonObject jsonObject = JsonUtil.convertMapToJsonObject(bodyMap);

        String id = jsonObject.get("id").getAsString();
        String email = jsonObject.get("email").getAsString();
        String pw = jsonObject.get("pw").getAsString();

        JsonObject responseJsonObject = new JsonObject();

        UserEntity user = userRepository.findFirstById(id);

        //id.equals(user.getId())
        if(user != null) {
            responseJsonObject.addProperty("code", 332);
            responseJsonObject.addProperty("message", "중복된 아이디입니다.");

        }else if(pw.length() > 24){
            responseJsonObject.addProperty("code", 333);
            responseJsonObject.addProperty("message", "비밀번호는 24자리 미만으로 입력해주세요.");

        }else {

            String hashedPw = BCrypt.hashpw(pw, BCrypt.gensalt());
            UserEntity userEntity = new UserEntity();

            userEntity.setId(id);
            userEntity.setEmail(email);
            userEntity.setPw(hashedPw);
            userRepository.save(userEntity);


            responseJsonObject.addProperty("code", 200);
            responseJsonObject.addProperty("message", "회원가입에 성공!");
        }
        return responseJsonObject.toString();

    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST, produces = {"application/json"})
    public String signInController(@RequestBody Map<String,Object> bodyMap){

        JSONObject responseJsonObject = new JSONObject();
        String id = (String)bodyMap.get("id");
        String pw = (String)bodyMap.get("pw");
        UserEntity user = userRepository.findFirstById(id); // 일치하는게 없으면 user가 null
        String storedPw = user.getPw();
        if(user==null){
            //id가 없는 경우 처리 로직
            System.out.println("ID 없음");
            try {
                responseJsonObject.put("status", 101);
                responseJsonObject.put("message", "회원 ID 정보가 없습니다.");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            if(BCrypt.checkpw(pw, storedPw)){
                //로그인 정보 일치
                System.out.println("정상 정보");
                Integer idx = user.getUserIdx();
                Map<String, Object> payloads = new HashMap<>();
                payloads.put("idx", idx);
                payloads.put("id", id);
                String jwtString = JwtUtil.makeJwtTokenString(payloads);

                try {
                    responseJsonObject.put("status", 200);
                    responseJsonObject.put("message", "loginSuccess");
                    responseJsonObject.put("jwt", jwtString);

                    // jsonObject : JVM만 아는 바이트값

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                //비밀번호 불일치
                System.out.println("비밀번호 불일치");
                try {
                    responseJsonObject.put("status", 102);
                    responseJsonObject.put("message", "비밀번호가 잘못 입력됐습니다.");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        // id,pw 입력받음
        // id, pw db에 있는지 확인 함 유효한지 확인
        // 유효하다면 db에 idx,id 가지고 jwt 만듦
        // 만든 jwt return

        return responseJsonObject.toString();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String viewUser(@RequestHeader Map<String, String>headerMap) throws JSONException{
        JSONObject responseJsonObject = new JSONObject();
        String jwtTokenString= headerMap.get("x-access-token");
        if(StringUtils.isEmpty(jwtTokenString)){
            // == (null || "")
            System.out.println("JWT Token String 미수신");

            try {
                responseJsonObject.put("status", 103);
                responseJsonObject.put("message", "Bad Request");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else{

            Claims claims = null;
            try {
                claims = JwtUtil.getPayloadsFromJwtString(jwtTokenString);
                Integer idx = claims.get("idx", Integer.class); // (key, key의 value값의 type)
                UserEntity userEntity = userRepository.findFirstByUserIdx(idx);
                    responseJsonObject.put("status", 200);
                    responseJsonObject.put("message", "내 정보 보기 성공");
                    responseJsonObject.put("id", userEntity.getId());
                    responseJsonObject.put("email", userEntity.getEmail());
            } catch (ExpiredJwtException expiredJwtException) {
                responseJsonObject.put("status", 105);
                responseJsonObject.put("message", "토큰 유효기간 만료");
            } catch (SignatureException e) {
                responseJsonObject.put("status", 106);
                responseJsonObject.put("message", "Wrong Key Value");
            } catch (MalformedJwtException e){
                responseJsonObject.put("status", 107);
                responseJsonObject.put("message", "잘못된 Json 내용");
            }


        }
        return responseJsonObject.toString();
    }

}
