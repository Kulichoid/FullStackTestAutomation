package cz.crowire.be_tests.restassured;

import cz.crowire.be_tests.common.LeadDto;
import cz.crowire.be_tests.config.RestAssuredConfigSetup;
import io.qameta.allure.*;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

@Slf4j
public class LeadControllerRestAssuredTest extends RestAssuredConfigSetup {

  // DataProvider supplies test data for createNewLead test
  @DataProvider(name = "leadDataProvider")
  public LeadDto[][] leadDataProvider() {
    return new LeadDto[][] {
      {new LeadDto("John Doe", "john.doe@example.com")}, // valid lead data
      {new LeadDto("Jane Doe", "jane.doe@example.com")}, // valid lead data
      {new LeadDto("Invalid Lead", "")} // invalid lead data (empty email)
    };
  }

  // Test method for creating a new lead, uses data from DataProvider
  @Test(description = "Test for creating a new lead", dataProvider = "leadDataProvider")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Create a new lead using the API and verify its response")
  @Step("Create new Lead")
  public Response createNewLead(LeadDto body) {
    // Sends a POST request to create a new lead and validates the response
    Response response = sendCreateLeadRequest(body.getName(), body.getEmail());
    // Define expected fields and values for validation
    Map<String, Object> expectedFields = new HashMap<>();
    expectedFields.put("name", body.getName());
    expectedFields.put("email", body.getEmail());
    // Validate the response using the generic method
    validateApiResponse(response, 201, expectedFields);
    return response;
  }

  // Test method for getting all leads
  @Test(description = "Get all leads")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Get all existing leads using the API and verify its response")
  @Step("Delete lead")
  public void getAllLeads() {
    validateApiResponse(sendGetLeadRequest(), 200, null);
  }

  // Test method for deleting leads
  @Test(description = "Test for deleting leads")
  @Severity(SeverityLevel.NORMAL)
  @Description("Create a new lead using the API and verify its response")
  @Step("Delete lead")
  public void deleteLead() {
    // Create new testing lead for deleting
    LeadDto leadDto = new LeadDto("Delete", "delete@me.com");
    int leadId = createNewLead(leadDto).getBody().path("id");
    log.info("New testing leadId:{}", leadId);
    // Sends a POST request to delete lead
    validateApiResponse(sendDeleteLeadRequest(leadId), 204, null);
  }

  @Step("Prepare request body for new Lead")
  public LeadDto prepareCreateLeadRequest(String name, String email) {
    // Creates and returns a new LeadDto object
    return new LeadDto(name, email);
  }

  @Step("Send POST request to create a lead")
  private Response sendCreateLeadRequest(String name, String email) {
    // Sends a POST request with LeadDto as the body and returns the response
    Response response =
        given()
            .body(
                prepareCreateLeadRequest(
                    name, email)) // Prepares the request body with lead details
            .when()
            .post() // Sends a POST request to create the lead
            .then()
            .log()
            .ifValidationFails()
            .extract()
            .response(); // Extracts and returns the response

    // Attaches the response body to the Allure report
    Allure.addAttachment("Response Body", response.asString());
    return response;
  }

  @Step("Send POST request to delete a lead")
  private Response sendDeleteLeadRequest(int leadId) {
    // Sends a POST request with LeadId param
    Response response =
        given()
            .pathParam("leadId", leadId)
            .when()
            .delete("{leadId}")
            .then()
            .log()
            .ifValidationFails()
            .extract()
            .response();

    // Attaches the response body to the Allure report
    Allure.addAttachment("Response Body", response.asString());
    return response;
  }

  @Step("Send POST request to delete a lead")
  private Response sendGetLeadRequest() {
    // Sends a GET request
    Response response = given().when().get().then().log().ifValidationFails().extract().response();

    // Attaches the response body to the Allure report
    Allure.addAttachment("Response Body", response.asString());
    return response;
  }

  @Step("Validate the API response")
  public void validateApiResponse(
      Response response, int expectedStatusCode, Map<String, Object> expectedFields) {
    // Assert the status code matches the expected status code
    assertEquals(response.statusCode(), expectedStatusCode, "Unexpected status code");

    // If expected status code is 2xx, optionally validate fields if expectedFields is provided
    if (response.statusCode() >= 200 && response.statusCode() < 300) {
      // Only validate fields if the expectedFields map is not null or empty
      if (expectedFields != null && !expectedFields.isEmpty()) {
        for (Map.Entry<String, Object> entry : expectedFields.entrySet()) {
          String fieldName = entry.getKey();
          Object expectedValue = entry.getValue();
          Object actualValue = response.jsonPath().get(fieldName);
          // Validate each expected field
          assertEquals(actualValue, expectedValue, "Field '" + fieldName + "' does not match");
        }
      }
    } else {
      // Log and attach error details to Allure if the response is not successful
      log.error("Unexpected API response: {}", response.asString());
      Allure.addAttachment("Error Response", response.asString());
      fail("API returned unexpected status code: " + response.statusCode());
    }

    // Attach the full response body to Allure report
    Allure.addAttachment("Response Body", response.asString());
  }
}
