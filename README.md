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
- The test case design basically using user journey or end to end approach. And also there will be significant separate test scenario between positive and negative cases. 
- There is TestRunner class for executing all the features test case on directory src/test/resources/features/
- All the test scenarios stored in feature file and grouped by its main module (Pet, Store, User)
- All the step defintions of each test steps are stored in java class with name same as its feature file
- There is a HTML report generated after run the test that is located on target/cucumber-reports.html

# API test automation framework room for improvement
Basically, at this point, this framework still only asserting mostly on two points:
1. Response code
2. Response body
But ofcourse, there are still a lot of room for improvement around this framework. Some of them are:
1. Verification of response header
2. Verification of response payload in terms of verify correct field name, type, and value
3. Validation of performance
