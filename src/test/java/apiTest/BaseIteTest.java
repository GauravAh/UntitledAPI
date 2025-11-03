package apiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import core.RestWrapper;
import reporting.ExtentReportNg;
import utils.PayloadProcessor;

import java.util.Map;

public abstract class BaseIteTest<T extends dto.BaseItePojo> {

    abstract String getPayloadPath();
    abstract String getUrlPath();
    abstract String getEndpointProperty();

    public void runTest(T testData) throws JsonProcessingException {

        String expectedResult =  testData.expectedoutput();
        System.out.println("Expected Result is.." + expectedResult);
        String expectedJson = "{\n" +
                "    \"bookingid\": 815,\n" +
                "    \"booking\": {\n" +
                "        \"firstname\": \"JimTest\",\n" +
                "        \"lastname\": \"Brown\",\n" +
                "        \"totalprice\": 111,\n" +
                "        \"depositpaid\": true,\n" +
                "        \"bookingdates\": {\n" +
                "            \"checkin\": \"2018-01-01\",\n" +
                "            \"checkout\": \"2019-01-01\"\n" +
                "        },\n" +
                "        \"additionalneeds\": \"Breakfast\"\n" +
                "    }\n" +
                "}";

        String actualJson = "{\n" +
                "    \"bookingid\": 5085,\n" +
                "    \"booking\": {\n" +
                "        \"firstname\": \"JimTest\",\n" +
                "        \"lastname\": \"Brown\",\n" +
                "        \"totalprice\": 111,\n" +
                "        \"depositpaid\": true,\n" +
                "        \"bookingdates\": {\n" +
                "            \"checkin\": \"2018-01-01\",\n" +
                "            \"checkout\": \"2019-01-01\"\n" +
                "        },\n" +
                "        \"additionalneeds\": \"Breakfast\"\n" +
                "    }\n" +
                "}";

        String headerparams = "application/json";
        String method="POST";
        String fullUrl = getUrlPath() + getEndpointProperty();
        ExtentReportNg.log("Full Url" + fullUrl);
        Map<String,Object> conToMap = PayloadProcessor.convertToMap(testData);
        for(Map.Entry<String,Object> printingMap : conToMap.entrySet()){
            System.out.println(printingMap.getKey() + "------>" + printingMap.getValue());
        }
        String payload = PayloadProcessor.getPayload(getPayloadPath(),conToMap);
       // ExtentReportNg.log("<pre>" + actualResponse + "</pre>");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedRoot = mapper.readTree(expectedJson);
        JsonNode actualRoot = mapper.readTree(actualJson);


    }
    
    public void compareJson(JsonNode expected, JsonNode actual, String path){



    }

}
