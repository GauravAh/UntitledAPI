package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class PayloadProcessor {

    // 60 piece petha, pipe, mobile, haircut, Car

    public static String getPayload(String payloadPath,Map<String,Object> hMap){
        try {
            // Step 1: Read payload template from file
            String payload = new String(Files.readAllBytes(Paths.get(payloadPath)));

            // Step 2: Replace placeholders {key} with actual values from hMap
            for (Map.Entry<String, Object> entry : hMap.entrySet()) {
                String key = entry.getKey();
                String value = String.valueOf(entry.getValue());
                payload = payload.replace("{" + key + "}", value);
            }
            // Step 3: Return final payload
            return payload;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static  <T> Map<String, Object> convertToMap(T testData) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> dataMap = mapper.convertValue(testData, Map.class);
        return dataMap;
    }

}
