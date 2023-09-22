package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.AddPlace;
import resources.Endpoints;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition  extends Utils {

    RequestSpecification req;

    // ResponseSpecification res;
    Response response;
    TestDataBuild payloadData = new TestDataBuild();
    static String place_id;




    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {



        req = given().spec(requestSpecification())
                .body(payloadData.add_Place_Payload(name, language, address));

    }


    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String endpoint, String method) {

        // res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        Endpoints endpointResource = Endpoints.valueOf(endpoint);
        System.out.println(endpointResource.getEndpoint());

        if(method.equalsIgnoreCase("POST")) {
            response = req.when().post(endpointResource.getEndpoint());
        }

        else if (method.equalsIgnoreCase("GET")) {
            response = req.when().get(endpointResource.getEndpoint());
        }

        else if (method.equalsIgnoreCase("DELETE")) {
            response = req.when().delete(endpointResource.getEndpoint());
        }


    }

    @Then("The API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(int statusCode) {

        assertEquals(statusCode,response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {


        assertEquals(getValueOf(response, keyValue),expectedValue);
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String endpoint) throws IOException {


        place_id = getValueOf(response,"place_id");
        req = given().spec(requestSpecification())
                .queryParam("place_id",place_id);

        user_calls_with_http_request(endpoint,"GET");
        String actualName = getValueOf(response, "name");
        Assert.assertEquals(actualName, expectedName);

    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {

        req = given().spec(requestSpecification()).
                body(payloadData.delete_Place_Payload(place_id));



    }






}

