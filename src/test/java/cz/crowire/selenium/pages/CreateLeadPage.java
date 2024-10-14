package cz.crowire.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class CreateLeadPage {
  private final WebDriver driver;

  // Locators for the input fields and the submit button
  private final By nameField = By.id("name");
  private final By emailField = By.id("email");
  private final By submitButton = By.xpath("//button[@type='submit']");

  // Constructor
  public CreateLeadPage(WebDriver driver) {
    this.driver = driver;
  }

  // Method to enter name
  public void enterName(String name) {
    driver.findElement(nameField).sendKeys(name);
  }

  // Method to enter email
  public void enterEmail(String email) {
    driver.findElement(emailField).sendKeys(email);
  }

  // Method to submit the form
  public void submitForm() {
    driver.findElement(submitButton).click();
  }

  public String getValidationMessage(By element) {
    WebElement emailElement = driver.findElement(element);
    return emailElement.getAttribute("validationMessage");
  }

  public void isFieldValidated(By element) {
    WebElement emailElement = driver.findElement(element);
    Objects.requireNonNull(emailElement.getAttribute("required")).contains("true");
  }
}
