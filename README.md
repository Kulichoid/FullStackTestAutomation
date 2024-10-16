# FullStack Test Automation

This repository demonstrates my ability to automate both back-end (BE) and front-end (FE) testing using a full-stack application. The project utilizes:

- **RestAssured** for API (BE) testing
- **Selenium** for UI (FE) testing

## Features

- Automated tests for both BE and FE
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

## Project Structure
- `/test`: Source code for the automated tests.
  - `/restassured`: Contains API tests.
  - `/selenium`: Contains UI tests.

## Purpose
This project serves as a demo of my full-stack test automation skills.

## Future Enhancements
- Add more comprehensive tests for both BE and FE.
- Integrate CI/CD pipelines.
- Incorporate additional testing technologies such as Cypress and Playwright for further UI and end-to-end testing.
