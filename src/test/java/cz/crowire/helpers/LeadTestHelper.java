package cz.crowire.helpers;

import cz.crowire.common.LeadDto;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LeadTestHelper {

  // Method to create a basic lead
  public static int createTestLead(String name, String email) {
    LeadDto lead = new LeadDto();
    lead.setName(name);
    lead.setEmail(email);
    Response response =
        given().body(lead).when().post().then().log().ifValidationFails().extract().response();
    return response.getBody().path("id");
  }
}
