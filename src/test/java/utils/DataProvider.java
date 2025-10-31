package utils;

import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.poi.ss.formula.functions.T;
import org.testng.ITestContext;

import java.io.FileReader;
import java.util.List;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "Onboard")
    public Object[][] provideData(ITestContext context) throws ClassNotFoundException {
        String csvFilePath = context.getCurrentXmlTest().getParameter("csvFilePath");
        Class<?> pojoClass = Class.forName(context.getCurrentXmlTest().getParameter("pojoClass"));
        try {
            List<?> allData = new CsvToBeanBuilder<T>(new FileReader(csvFilePath))
                    .withType((Class) pojoClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            Object[][] result = new Object[allData.size()][1];
            for (int i = 0; i < allData.size(); i++) {
                result[i][0] = allData.get(i);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading CSV file or parsing POJO class: ");
            }

        }
}
