package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtentReportNg implements IReporter {

    private ExtentReports extent;
  //  private static final Logger logger = LogManager.getLogger(ExtentReportNg.class);
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final Map<String, String> hMap = new ConcurrentHashMap<>();

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        System.out.println("===== Custom TestNG Report =====");

        outputDirectory = "C:\\Users\\gaurav\\UntitledAPI\\";

        ExtentSparkReporter htmlReporter =  new ExtentSparkReporter(outputDirectory + "ExtentReport.html");
        htmlReporter.config().setReportName("Regression Suite Results");
        htmlReporter.config().setDocumentTitle("TestNG Automation Report");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        for (ISuite suite : suites) {
            String suiteName = suite.getName();
            System.out.println("Suite Name: " + suiteName);
            XmlSuite xmlSuite = suite.getXmlSuite();
            String nameOfXMLSuite =  xmlSuite.getName();
            String xmlParameter = xmlSuite.getParameter("env");
            Map<String, String> xmlParameters = xmlSuite.getParameters();
            for(Map.Entry<String,String> kk : xmlParameters.entrySet()){
                System.out.println(kk.getKey() + "-->" + kk.getValue());
            }
            String pathofXML =  xmlSuite.getFileName();

            // Each suite can have multiple results
            Map<String, ISuiteResult> suiteResults = suite.getResults();

            for (ISuiteResult result : suiteResults.values()) {
                ITestContext context = result.getTestContext();

                XmlTest getcurrentXmlTest = context.getCurrentXmlTest();
                String kk =  getcurrentXmlTest.getName();
                System.out.println(kk);

                String getParameter = getcurrentXmlTest.getParameter("env");
                System.out.println(getParameter);

                Map<String, String> getlocalParameter = getcurrentXmlTest.getLocalParameters();
                int kk1 =  getlocalParameter.size();
                for(Map.Entry<String,String> uu : getlocalParameter.entrySet()){
                    System.out.println(uu.getKey() + "---->" + uu.getValue());
                }

                Map<String, String> getAllParameter =getcurrentXmlTest.getAllParameters();
                for(Map.Entry<String,String> uu : getAllParameter.entrySet()){
                    System.out.println(uu.getKey() + "---->" + uu.getValue());
                }

                buildTestNodes(context.getPassedTests(), "PASS");
                buildTestNodes(context.getFailedTests(), "FAIL");
                buildTestNodes(context.getSkippedTests(), "SKIP");

            }
            extent.flush();
        }
    }

    private void buildTestNodes(IResultMap tests, String status) {
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                ExtentTest test = extent.createTest(result.getMethod().getMethodName());
              //  logger.info("Test Created------" + result.getTestClass().getRealClass());
                String key = makeKeyFromResult(result); // use same key builder

                if (hMap.containsKey(key)) {
                    // log the stored value (e.g. the URL), not the key
                    test.pass(hMap.get(key));
                }

                switch (status) {
                    case "PASS": test.pass("Test passed"); break;
                    case "FAIL": test.fail("Test failed"); break;
                    case "SKIP": test.skip("Test skipped"); break;
                }
            }

        }
    }

    public static void log(String log){
        ITestResult current = Reporter.getCurrentTestResult();
        if (current == null) {
          //  logger.warn("No current TestResult available to log: " + log);
            return;
        }
        String key = makeKeyFromResult(current);
        hMap.merge(key, log, (oldVal, newVal) -> oldVal + "<br>" + newVal);
    }

    private static String makeKeyFromResult(ITestResult result) {
        String pkg = result.getTestClass().getRealClass().getPackage().getName();
        String cls = result.getTestClass().getRealClass().getSimpleName();
        String method = result.getMethod().getMethodName(); // consistent: method name
        Object iterObj = result.getAttribute("iteration");
        String iteration = (iterObj != null) ? iterObj.toString() : "N/A";
        return pkg + cls + method + iteration;
    }

}

