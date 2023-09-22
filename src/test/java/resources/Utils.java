package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.ObjectUtils;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification reqSpecs;

    public String getValueOf(Response response, String key) {

        String stringResponse = response.asString();
        JsonPath jsonPath = new JsonPath(stringResponse);

        return jsonPath.get(key);
    }

    public RequestSpecification requestSpecification() throws IOException {

        if (reqSpecs == null) {

            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            reqSpecs = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseURI"))
                    .addQueryParam("key", "qaclick123")
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();

            return reqSpecs;
        }

        return reqSpecs;
    }

    public static String getGlobalValue(String baseURI) throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\taksarog\\Desktop\\Selenium\\FullFrameworkRESTAssured\\src\\test\\java\\resources\\GlobalData.properties");
        prop.load(fileInputStream);

        return prop.getProperty(baseURI);
    }
}
