package cz.crowire.selenium.tests;

import cz.crowire.selenium.pages.CreateLeadPage;
import cz.crowire.selenium.pages.LeadCreatedPage;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Slf4j
@Epic("Selenium tests")
public class CreateLeadTest {
  private WebDriver driver;
  private CreateLeadPage createLeadPage;
  private LeadCreatedPage leadCreatedPage;

  @BeforeClass
  @Step("Setup the WebDriver and navigate to the create lead page")
  public void setup() {
    log.info("Setting up WebDriver and initializing page objects.");
    driver = new ChromeDriver();
    driver.get("http://localhost:8080/create-lead");
    createLeadPage = new CreateLeadPage(driver);
  }

  @Test(description = "Test creating a new lead")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Test the creation of a new lead and verify the lead ID is displayed")
  @Step("Create a new lead with name and email")
  public void createNewLead() {
    log.info("Starting test for creating a new lead.");

    // Enter lead details
    setFieldName("John Doe");
    setFieldEmail("john.doe@example.com");

    // Submit the form
    createLeadPage.submitForm();
    log.info("Form submitted.");

    // Initialize LeadCreatedPage after form submission
    leadCreatedPage = new LeadCreatedPage(driver);

    // Verify the lead ID is displayed
    String leadId = leadCreatedPage.getLeadId();
    log.info("Lead ID returned: {}", leadId);
    Assert.assertNotNull(leadId, "Lead ID should not be null");

    // Attach the lead ID to the Allure report
    Allure.addAttachment("Lead ID", leadId);

    // Click the link to create another lead
    leadCreatedPage.clickCreateAnotherLead();
    log.info("Navigated back to the create lead page for another lead.");
  }

  @Test(description = "Check fields validation")
  @Severity(SeverityLevel.NORMAL)
  @Description("Validation test on the FE fields")
  @Step("Create lead without any parameters")
  public void checkFieldValidation() {

    // Try to submit the form without filling the fields
    createLeadPage.submitForm();

    // Check if the validation message for the name field is correct
    checkValidation("name");

    // Enter name
    setFieldName("John Doe");

    // Try to submit the form without email
    createLeadPage.submitForm();

    // Check if the validation message for the email field is correct
    checkValidation("email");
  }

  @Step("Check field validation")
  private void checkValidation(String id) {
    createLeadPage.isFieldValidated(By.id(id));
    String validationMessage = createLeadPage.getValidationMessage(By.id(id));
    Assert.assertEquals(
        validationMessage, "Vyplňte prosím toto pole.", "Validation message should be displayed");
    log.info("Validation for field {} correctly displayed", id);
  }

  @Step("Fill the Name field")
  private void setFieldName(String name) {
    createLeadPage.enterName(name);
  }

  @Step("Fill the email field")
  private void setFieldEmail(String email) {
    createLeadPage.enterName(email);
  }

  @AfterClass
  @Step("Tear down WebDriver after tests")
  public void tearDown() {
    log.info("Tearing down WebDriver.");
    if (driver != null) {
      driver.quit();
    }
  }
}
