package cz.crowire.be_tests.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class LeadControllerRestAssuredTest {
  @BeforeClass
  public void setup() {
    // Nastavení základní URL aplikace, kterou budeš testovat
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080; // přizpůsob podle konfigurace tvé aplikace
    RestAssured.basePath = "/api/leads";
  }

  @Test
  public void createNewLead() {
    given()
        .contentType(ContentType.JSON)
        .when()
        .body("asd")
        .log()
        .all()
        .post()
        .then()
        .statusCode(200) // Ověřuje, že se vrátí status 200 OK
        .body("size()", greaterThan(0)); // Ověřuje, že seznam leadů není prázdný
  }
}
