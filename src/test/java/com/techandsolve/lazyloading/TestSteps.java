package com.techandsolve.lazyloading;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * TestSteps to cucumber
 * Created by leo on 10/03/17.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class TestSteps {

    @LocalServerPort
    private int serverPort = 8080;

    private String url = "";

    private Response response;

    private RequestSpecification request;

    @Before
    public void setUp() {
        RestAssured.port = serverPort;
        request = given().log().all();
    }

    @Given("^the url (.+)$")
    public void withUrl(String url) {
        request = given().log().all();
        this.url = url;
    }

    @And("^with the parameter (.*) with the value (.*)$")
    public void withParam(String key, String value) {
        if (value != null) {
            request.param(key, value);
        }
    }

    @And("^with the file (.*) with the name (.*)$")
    public void withFile(String path, String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        request.multiPart(filename, path, resource.getInputStream());
    }

    @And("^with the text:$")
    public void withParamText(String text) {
        if (text != null) {
            request.body(text);
        }
    }

    @When("^make a DELETE$")
    public void callDELETE() {
        response = request.delete(url);
    }

    @When("^make a POST$")
    public void callPOST() {
        response = request.post(url);
    }

    @When("^make a GET$")
    public void callGET() {
        response = request.get(url);
    }


    @When("^make a PUT")
    public void callPUT() {
        response = request.put(url);
    }

    @Then("^the response status must be (\\d+)$")
    public void theResponseStatusIs(int status) {
        response.then().assertThat().statusCode(status);
    }

    @Then("^show the response$")
    public void showTheResponse() {
        response.body().print();
    }

    @And("^the response body must be list with size (\\d+)$")
    public void theResponseBodyMustContainArraySize(int size) {
        response.then().contentType(ContentType.JSON).body("size()", is(size));
    }

    @And("^the response json property (.*) must be (.*)$")
    public void theResponseBodyMustContainFieldWithValue(String field, String value) {
        if (value != null && !value.isEmpty()) {
            response.then().assertThat().body(field, equalTo(new BigInteger(value).intValue()));
        }
    }

    @And("^the response must be:$")
    public void theResponseBodyMustBe(String value) {
        response.then().assertThat().body(equalTo(value));
    }
}
