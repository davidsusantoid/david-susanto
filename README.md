This repo contains 2 parts:
1. Exploratory Test Charter
2. API test automation framework

# How to execute API test automattion:
1. Clone this repo to your local
2. Go to directory api-test
3. Type mvn test
4. Wait until test execution finished and see the log or report

# About this API test automation framework:
- This framework developed using BDD concept with Cucumber Java, RestAssured, Maven, and TestNG
- There is TestRunner class for executing all the features test case on directory src/test/resources/features/
- All the test scenarios stored in feature file and grouped by its main module (Pet, Store, User)
- All the step defintions of each test steps are stored in java class with name same as its feature file
- There is a HTML report generated after run the test that is located on target/cucumber-reports.html
