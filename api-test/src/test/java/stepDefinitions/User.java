package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class User {

    private static final String BASE_URL = "https://petstore3.swagger.io/api/v3";

    private static Response response;

    @When("Add new username {string} and password {string}")
    public void addNewUserName(String userName, String password) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        String reqBody = "{\"id\":303," +
            "\"username\":\"" + userName + "\"," +
            "\"firstName\":\"John\"," +
            "\"lastName\":\"James\"," +
            "\"email\":\"john@email.com\"," +
            "\"password\":\"" + password + "\"," +
            "\"phone\":\"12345\"," +
            "\"userStatus\":1}";
        response = requestSpecification.body(reqBody).post("/user");

        Assert.assertEquals(200, response.getStatusCode());
    }



    @When("Update username {string}")
    public void updateUserName(String userName) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        String reqBody = "{\"id\":303," +
                "\"username\":\"" + userName + "\"," +
                "\"firstName\":\"John\"," +
                "\"lastName\":\"James\"," +
                "\"email\":\"john@email.com\"," +
                "\"password\":\"12345\"," +
                "\"phone\":\"12345\"," +
                "\"userStatus\":1}";
        response = requestSpecification.body(reqBody).put("/user/" + userName);

        Assert.assertEquals(200, response.getStatusCode());
    }



    @Given("If username {string} is exist, then delete it")
    public void deleteUserNameifExist(String userName) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/user/" + userName);

        if (response.getStatusCode() == 200) {
            requestSpecification.delete("/user/" + userName);
        }

        response = requestSpecification.get("/user/" + userName);
        Assert.assertEquals(404, response.getStatusCode());
    }

    @When("Delete username {string}")
    public void deleteUserName(String userName) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.delete("/user/" + userName);

        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("Delete username <userName> with invalid input")
    public void deleteUserNameWithInvalidInput(int userName) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.delete("/user/" + userName);

        Assert.assertEquals(400, response.getStatusCode());
    }

    @When("Delete username {string} for user not found")
    public void deleteUserNameForUserNotFound(String userName) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.delete("/user/" + userName);

        Assert.assertEquals(404, response.getStatusCode());
    }



    @Then("Username {string} is found")
    public void userNameIsFound(String userName) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/user/" + userName);

        String jsonString = response.asString();
        String username = JsonPath.from(jsonString).get("username");

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(username, userName);
    }

    @Then("Username {string} was updated with {string}")
    public void userNameWasUpdated(String userName, String updatedUserName) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/user/" + userName);

        String jsonString = response.asString();
        String username = JsonPath.from(jsonString).get("username").toString();

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(updatedUserName, username);
    }

    @Then("Username {string} is not found")
    public void userNameIsNotFound(String userName) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/user/" + userName);

        String jsonString = response.asString();

        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("User not found", jsonString);
    }

    @Then("Get username <userName> with invalid input")
    public void getUserNameWithInvalidInput(int userName) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/user/" + userName);

        Assert.assertEquals(400, response.getStatusCode());
    }

    @Then("Login with username {string} and password {string}")
    public void loginWithUserNameAndPassword(String userName, String password) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/user/login?username=" + userName + "&password=" + password);

        Assert.assertEquals(200, response.getStatusCode());
    }
}
