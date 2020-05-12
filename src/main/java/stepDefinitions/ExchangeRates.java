package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.Utils;

public class ExchangeRates extends Utils {
	// declaring global variables
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	Response response;

	// calling requestSpec() method from Utils class
	@Given("^rates for foreign exchange rates$")
	public void rates_for_foreign_exchange_rates() throws Throwable {
		reqspec = given().spec(requestSpec());
	}

	// this method is used to call GET Method
	@When("^user calls Get http request with (.+)$")
	public void user_calls_get_http_request_with(String uri) throws Throwable {
		response = reqspec.get(uri);
	}

	// Validating response status code which is 200
	@Then("^user gets success with status code 200$")
	public void user_gets_success_with_status_code_200() throws Throwable {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	// Validating response key value from Json response
	@And("^(.+) in response body is (.+)$")
	public void in_response_body_is(String keyValue, String expectedValue) throws Throwable {
		Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);

	}

	// Validating negative and corner cases
	@When("^user calls invalid Get http request with (.+)$")
	public void user_calls_invalid_get_http_request_with(String uri) throws Throwable {
		response = reqspec.get(uri);
	}

	// Validating response code for invalid Url
	@Then("^user gets error message with status code 400$")
	public void user_gets_error_message_with_status_code_400() throws Throwable {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);
	}

}
