package cz.crowire.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeadCreatedPage {
  private final WebDriver driver;

  // Locator for the lead ID element
  private final By leadIdText = By.id("leadId");
  private final By createAnotherLeadLink = By.linkText("Create another lead");

  // Constructor
  public LeadCreatedPage(WebDriver driver) {
    this.driver = driver;
  }

  // Method to get the lead ID text
  public String getLeadId() {
    return driver.findElement(leadIdText).getText();
  }

  // Method to navigate back to the create lead form
  public void clickCreateAnotherLead() {
    driver.findElement(createAnotherLeadLink).click();
  }
}
