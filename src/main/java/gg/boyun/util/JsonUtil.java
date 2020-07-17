package gg.boyun.util;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.util.Map;
public class JsonUtil {
    private static Gson gson = new Gson();
    public static JsonObject convertMapToJsonObject(Map<String, Object>inputMap){
//        String jsonString = gson.toJson(inputMap);
//        JsonParser jsonParser = new JsonParser();

//        JsonObject convertedJsonObject = jsonParser.parse(jsonString).getAsJsonObject();

        JsonObject convertedJsonObject = gson.toJsonTree(inputMap).getAsJsonObject();
        return convertedJsonObject;
    }
}
