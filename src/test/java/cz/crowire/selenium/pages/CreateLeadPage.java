package cz.crowire.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateLeadPage {
    private WebDriver driver;

    // Locators for the input fields and the submit button
    private By nameField = By.id("name");
    private By emailField = By.id("email");
    private By submitButton = By.xpath("//button[@type='submit']");

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
}