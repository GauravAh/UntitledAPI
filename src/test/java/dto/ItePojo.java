package dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ItePojo implements BaseItePojo {

    @CsvBindByName(column = "testcaseno")
    public String testcaseno;

    @CsvBindByName(column = "type")
    public String type;

    @CsvBindByName(column = "testcaseid")
    private String testCaseID;

    @CsvBindByName(column = "testdescription")
    private String testDescription;

    @CsvBindByName(column = "firstname")
    public String firstname;

    @CsvBindByName(column = "lastname")
    private String lastname;

    @CsvBindByName(column = "totalprice")
    private String totalprice;

    @CsvBindByName(column = "depositpaid")
    public String depositpaid;

    @CsvBindByName(column = "checkin")
    private String checkin;

    @CsvBindByName(column = "checkout")
    private String checkout;

    @CsvBindByName(column = "additionalneeds")
    public String additionalneeds;

    @CsvBindByName(column = "expectedoutput")
    public String expectedoutput;

    @CsvBindByName(column = "excludekeyscomparison")
    private String excludekeyscomparison;

    @CsvBindByName(column = "headers")
    public String headers;

    @Override
    public String expectedoutput() {
        return expectedoutput;
    }
}
