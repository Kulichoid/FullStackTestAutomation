package cz.crowire.be_tests.restassured;

import cz.crowire.be_tests.common.LeadDto;
import cz.crowire.be_tests.config.RestAssuredConfigSetup;
import io.qameta.allure.*;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


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
    validateResponse(response, body.getName(), body.getEmail());
    return response;
  }

  // Test method for deleting leads
  @Test(description = "Test for deleting leads")
  @Severity(SeverityLevel.NORMAL)
  @Description("Create a new lead using the API and verify its response")
  @Step("Delete lead")
  public void deleteLead() {
    // Create new testing lead for deleting
    LeadDto leadDto = new LeadDto("Delete","delete@me.com");
    int leadId = createNewLead(leadDto).getBody().path("id");
    log.info("New leadId:{}", leadId);
    // Sends a POST request to delete lead
    Response response = sendDeleteLeadRequest(leadId);
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
        given().log().all()
            .body(
                prepareCreateLeadRequest(
                    name, email)) // Prepares the request body with lead details
            .when()
            .post() // Sends a POST request to create the lead
            .then()
            .log().all() // Logs the response only if validation fails
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

  // Step to validate the API response for creating a lead
  @Step("Validate the response from Create Lead API")
  private void validateResponse(Response response, String expectedName, String expectedEmail) {
    if (response.statusCode() == 201) {
      // Extracts and validates fields from the response
      String leadId = response.jsonPath().getString("id");
      String name = response.jsonPath().getString("name");
      String email = response.jsonPath().getString("email");

      // Asserts that the lead ID is not null and is a valid number
      assertNotNull(leadId, "Lead ID should not be null");
      assertTrue(leadId.matches("\\d+"), "Lead ID should be a number");

      // Asserts that the response name and email match the expected values
      assertEquals(name, expectedName, "Lead name should match");
      assertEquals(email, expectedEmail, "Lead email should match");
    } else {
      // Logs and reports an error if the status code is not 201
      log.warn("API returned error: {}", response.statusCode());
      assertTrue(response.statusCode() >= 400, "Expected error status code");
      Allure.addAttachment("Error Response", response.asString());
    }
  }
}
