package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestData;
import resources.Utils;


import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {
    RequestSpecification req;
    ResponseSpecification resReq;
    Response response;
    TestData testData =new TestData();
    static String place_id;
    APIResources  APIReq;

    @Given("Add place {string}  {string} {double}")
    public void add_place(String language, String name , Double lat) throws IOException {
        //Generate MAin request with body
         req =  given().spec(requestSpec()).body(testData.addplacedata(language ,name ,lat));
    }


    @When("user calls {string} with {string} request")
    public void user_calls_with_request(String APIRequest, String APIMethod)  {
        //call the post request and get the response
        APIReq=APIResources.valueOf(APIRequest);
        if (APIMethod.equalsIgnoreCase("post"))
            response=  req.when().post(APIReq.getResource()).then().extract().response();
        else if (APIMethod.equalsIgnoreCase("get"))
            response= req.when().get(APIReq.getResource()).then().extract().response();
        else if(APIMethod.equalsIgnoreCase("delete"))
            response= req.when().delete(APIReq.getResource()).then().extract().response();
    }


    @Then("API call got success with status code  {int}")
    public void api_call_got_success_with_status_code(Integer int1) {
        //Assertion on the status code
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @Then("{string} in response body  is {string}")
    public void in_response_body_is(String key, String expectedValue) {
       // Assertion on the response body data (status and scope)
        String keyValue = JsonParse(response,key);
       Assert.assertEquals(keyValue,expectedValue);

    }

   @Then("verify the added place_name mapped to {string} using {string}")
    public void verify_the_added_place_name_mapped_to_using(String Expected_name , String APIRequest) throws IOException {
       place_id = JsonParse(response,"place_id");
         req= given().spec(requestSpec()).queryParam("place_id",place_id);
          user_calls_with_request(APIRequest,"GET");

       String Actual_name =JsonParse(response,"name");
        System.out.println(Actual_name);
          Assert.assertEquals(Expected_name,Actual_name);

    }

    @Given("place id")
    public void place_id_lat() throws IOException {
        //set place id on the api request
        req= given().spec(requestSpec()).body(testData.delete_body(place_id));
    }



}
