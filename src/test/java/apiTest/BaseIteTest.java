package apiTest;

import core.RestWrapper;
import reporting.ExtentReportNg;
import utils.PayloadProcessor;

import java.util.Map;

public abstract class BaseIteTest<T extends dto.BaseItePojo> {

    abstract String getPayloadPath();
    abstract String getUrlPath();
    abstract String getEndpointProperty();

    public void runTest(T testData) throws Exception {

        String expectedResult =  testData.expectedoutput();
        System.out.println("Expected Result is.." + expectedResult);
        String expectedOutcome = "{\n" +
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

        String payload1 = "{ \"firstname\": \"Jim\", \"lastname\": \"Brown\", \"totalprice\": 111, \"depositpaid\": true, \"bookingdates\": { \"checkin\": \"2018-01-01\", \"checkout\": \"2019-01-01\" }, \"additionalneeds\": \"Breakfast\" }";


        String headerparams = "application/json";
        String method="POST";
        String fullUrl = getUrlPath() + getEndpointProperty();
        System.out.println(fullUrl);
        ExtentReportNg.log(fullUrl);
        Map<String,Object> conToMap = PayloadProcessor.convertToMap(testData);
        for(Map.Entry<String,Object> printingMap : conToMap.entrySet()){
            System.out.println(printingMap.getKey() + "------>" + printingMap.getValue());
        }
        String payload = PayloadProcessor.getPayload(getPayloadPath(),conToMap);
        ExtentReportNg.log(payload);
        String payloadResponse = RestWrapper.sendPostRequest(fullUrl,payload1);
        System.out.println("Payload Response is.." + payloadResponse);

    }

}
