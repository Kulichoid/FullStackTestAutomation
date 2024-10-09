package cz.crowire.be_tests.config;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.testng.annotations.BeforeClass;

public class RestAssuredConfigSetup {

  @BeforeClass
  public void setupRestAssured() {
    // Set the base URI of the API being tested
    RestAssured.baseURI = "http://localhost"; // Default localhost URI for API testing
    // Set the port on which the API is running
    RestAssured.port = 8080; // Port where the server is hosted
    // Set the base path for all API endpoints being tested (e.g., /api/leads)
    RestAssured.basePath = "/api/leads"; // Base path for the API endpoints

    // Define the default request specification for all tests
    RestAssured.requestSpecification =
        RestAssured.given()
            .header("Content-Type", "application/json")
            .config(
                RestAssuredConfig.config()
                    .httpClient(
                        HttpClientConfig.httpClientConfig()
                            // Set connection timeout and socket timeout to 10 seconds
                            .setParam("http.connection.timeout", 10000)
                            .setParam("http.socket.timeout", 10000)))
            .log()
            .ifValidationFails();
  }
}
