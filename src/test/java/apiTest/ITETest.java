package apiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import dto.ItePojo;
import org.testng.annotations.Test;
import reporting.ExtentReportNg;
import utils.Constants;
import utils.DataProvider;

@Test
public class ITETest extends BaseIteTest {

    public static String ItePath =  Constants.POP_Payload_Path + Constants.POP_ITE_JSON_FileName;

    public static String IteUrl = Constants.ITE_Url;

    public static String IteEndpoint = Constants.ITE_Endpoint;


    @Override
    String getPayloadPath() {
        return ItePath;
    }

    @Override
    String getUrlPath() {
        return IteUrl;
    }

    @Override
    String getEndpointProperty() {
        return IteEndpoint;
    }

    @Test(dataProvider = "Onboard", dataProviderClass = DataProvider.class)
    public void runTestCase(ItePojo data) throws JsonProcessingException {
        runTest(data);
    }

}
