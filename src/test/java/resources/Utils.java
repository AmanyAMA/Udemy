package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.internal.dynalink.beans.StaticClass;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification specReq;
    public RequestSpecification requestSpec() throws IOException {

        if (specReq==null) {
            PrintStream stream = new PrintStream(new FileOutputStream("logFile.txt"));
            //Create separated class to set the Generic Request data
            specReq = new RequestSpecBuilder().setBaseUri(GetGlobalValues("BaseUri"))
                    .addFilter(RequestLoggingFilter.logRequestTo(stream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                    .setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
            return specReq;
       }
       return specReq;

    }

    public String JsonParse(Response response, String keyValue){
        JsonPath jsonPath = new JsonPath(response.asString());
        String key = jsonPath.get(keyValue).toString();
        return key;
    }
    public String GetGlobalValues(String key) throws IOException {

        // create a reader object on the properties file
        FileReader reader = new FileReader("D:\\OupAPI\\Udemy\\framework\\src\\test\\java\\resources\\global.properties");
        // create properties object
        Properties p = new Properties();
        // Add a wrapper around reader object
        p.load(reader);
        // access properties data
        return   p.getProperty(key);

        /*Properties prop = new Properties();
        FileInputStream propfile = new FileInputStream("D:\\OupAPI\\Udemy\\framework\\src\\test\\java\\resources\\global.properties");
        prop.load(propfile);
        prop.getProperty(key);
        return  prop.getProperty(key);*/
    }


}
