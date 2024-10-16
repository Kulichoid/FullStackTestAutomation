# FullStack Test Automation

This repository demonstrates my ability to automate both back-end (BE) and front-end (FE) testing using a full-stack application. The project utilizes:

- **RestAssured** for API (BE) testing
- **Selenium** for UI (FE) testing

## Features

- Automated tests for both BE and FE
- Reporting of test results
- Includes a sample application that can be tested
- Easily extendable with more test scenarios

## How to Run the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/Kulichoid/FullStackTestAutomation.git

2. Navigate to the project directory:
   ```bash
   cd FullStackTestAutomation

3. Run the application using the provided .jar file:
   ```bash
    java -jar .\bin\lead-management-1.0.0.jar

This will start the lead management application, which serves as the target for automated tests.

## How to Run the Tests
- API Tests: Navigate to the restassured package and execute the tests with testNG.
- UI Tests: Run the Selenium-based tests in the selenium package.
- Tests can be run using the following Gradle command:
  ```bash
  ./gradlew test --tests "cz.crowire.restassured.tests.LeadControllerRestAssuredTest"

## Generating Test Reports
- Generate detailed reports using Allure by running the following command:
  <img width="1920" alt="2024-10-16 16_43_12-" src="https://github.com/user-attachments/assets/650dbca2-0df0-462a-b705-7ea62e990e92">
  <img width="1918" alt="2024-10-16 16_43_50-Test results - Class Gradle Test Executor 1" src="https://github.com/user-attachments/assets/b2bc4a26-6c57-4993-a13a-a88dfd21d06f">

  ```bash
   allure serve .\build\allure-results\

## Project Structure
- `/test`: Source code for the automated tests.
  - `/restassured`: Contains API tests.
  - `/selenium`: Contains UI tests.

## Future Enhancements
- Add more comprehensive tests for both BE and FE.
- Integrate CI/CD pipelines.
- Incorporate additional testing technologies such as Cypress and Playwright for further UI and end-to-end testing.
