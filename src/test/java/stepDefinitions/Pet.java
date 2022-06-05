package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;

public class Pet {

    private static final String BASE_URL = "https://petstore3.swagger.io/api/v3";

    private static Response response;

    @When("Add new pet by ID {int}, name {string}, tagname {string}, and status {string}")
    public void addNewPetByID(int petId, String name, String tagName, String status) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        String reqBody = "{\"id\":" + petId + "," +
                "\"name\":\"" + name + "\"," +
                "\"category\":{\"id\":1,\"name\":\"Dogs\"}," +
                "\"photoUrls\":[\"string\"]," +
                "\"tags\":[{\"id\":0,\"name\":\"" + tagName + "\"}]," +
                "\"status\":\"" + status + "\"}";
        response = requestSpecification.body(reqBody).post("/pet");

        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("Add new pet by ID with invalid input {string}")
    public void addNewPetByIDToTheStoreWithInvalidInput(String invalidPetId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        String reqBody = "{\"id\":\"" + invalidPetId + "\"," +
                "\"name\":\"doggie\"," +
                "\"category\":{\"id\":1,\"name\":\"Dogs\"}," +
                "\"photoUrls\":[\"string\"]," +
                "\"tags\":[{\"id\":0,\"name\":\"string\"}]," +
                "\"status\":\"available\"}";
        response = requestSpecification.body(reqBody).post("/pet");

        Assert.assertEquals(400, response.getStatusCode());
    }



    @When("Update pet by ID {int} with name to be {string} and status to be {string}")
    public void updatePetByID(int petId, String updatedName, String updatedStatus) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        String reqBody = "{\"id\":" + petId + "," +
                "\"name\":\"" + updatedName + "\"," +
                "\"category\":{\"id\":1,\"name\":\"Dogs\"}," +
                "\"photoUrls\":[\"string\"]," +
                "\"tags\":[{\"id\":0,\"name\":\"string\"}]," +
                "\"status\":\"" + updatedStatus + "\"}";
        response = requestSpecification.body(reqBody).put("/pet");

        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("Update pet by ID with invalid input {string}")
    public void updatePetByIDwithInvalidInput(String invalidPetId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        String reqBody = "{\"id\":\"" + invalidPetId + "\"," +
                "\"name\":\"dog\"," +
                "\"category\":{\"id\":1,\"name\":\"Dogs\"}," +
                "\"photoUrls\":[\"string\"]," +
                "\"tags\":[{\"id\":0,\"name\":\"string\"}]," +
                "\"status\":\"available\"}";
        response = requestSpecification.body(reqBody).put("/pet");

        Assert.assertEquals(400, response.getStatusCode());
    }

    @When("Update pet by ID {int} using form data with name to be {string} and status to be {string}")
    public void updatePetByIDWithFormData(int petId, String updatedName, String updatedStatus) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.post("/pet/" + petId + "?name=" + updatedName + "&status=" + updatedStatus);

        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("Update pet by ID using form data with invalid input {string}")
    public void updatePetByIDUsingFormDataWithInvalidInput(String invalidPetID) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.post("/pet/" + invalidPetID + "?name=dog&status=available");

        Assert.assertEquals(400, response.getStatusCode());
    }

    @When("Update pet by ID with pet ID {int} not found")
    public void updatePetByIDnotFound(int petId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        String reqBody = "{\"id\":" + petId + "," +
                "\"name\":\"dog\"," +
                "\"category\":{\"id\":1,\"name\":\"Dogs\"}," +
                "\"photoUrls\":[\"string\"]," +
                "\"tags\":[{\"id\":0,\"name\":\"string\"}]," +
                "\"status\":\"available\"}";
        response = requestSpecification.body(reqBody).put("/pet");

        Assert.assertEquals(404, response.getStatusCode());
    }



    @Given("If pet by ID {int} is exist, then delete it")
    public void deletePetByIDIfExist(int petId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/pet/" + petId);

        if (response.getStatusCode() == 200) {
            requestSpecification.delete("/pet/" + petId);
        }

        response = requestSpecification.get("/pet/" + petId);
        Assert.assertEquals(404, response.getStatusCode());
    }

    @When("Delete pet by ID {int}")
    public void deletePetByID(int petId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.delete("/pet/" + petId);

        String jsonString = response.asString();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Pet deleted", jsonString);
    }

    @When("Delete pet by ID with invalid input {string}")
    public void deletePetIDwithInvalidInput(String invalidPetId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.delete("/pet/" + invalidPetId);

        Assert.assertEquals(400, response.getStatusCode());
    }



    @Then("Pet by ID {int} is found")
    public void foundPetByID(int petId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/pet/" + petId);

        String jsonString = response.asString();
        int id = JsonPath.from(jsonString).get("id");

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(petId, id);
    }

    @Then("Pet by ID {int} was updated with name to be {string} and status to be {string}")
    public void petByIDwasUpdated(int petId, String updatedName, String updatedStatus) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/pet/" + petId);

        String jsonString = response.asString();
        String name = JsonPath.from(jsonString).get("name").toString();
        String status = JsonPath.from(jsonString).get("status").toString();

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(updatedName, name);
        Assert.assertEquals(updatedStatus, status);
    }

    @Given("Pet by ID {int} is not found")
    public void petByIDisNotFound(int petId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/pet/" + petId);

        String jsonString = response.asString();
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("Pet not found", jsonString);
    }

    @Then("Invalid pet ID {string} supplied")
    public void petByIDInvalidInputIsNotFound(String invalidPetID) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/pet/" + invalidPetID);

        Assert.assertEquals(400, response.getStatusCode());
    }

    @Then("Newly added pet ID {int} exist in query find pet by status {string}")
    public void newlyPetIDappearsInFindPetByStatus(int petId, String status) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/pet/findByStatus?status=" + status);

        List<Integer> ids = response.jsonPath().getList("id");
        boolean isExist = false;
        for (int id : ids) {
            if (id == petId) {
                isExist = true;
                break;
            }
        }

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(isExist);
    }

    @Then("All returned correct status in query find pet by status {string}")
    public void returnAllSameStatusInFindPetByStatus(String status) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/pet/findByStatus?status=" + status);

        List<String> statuses = response.jsonPath().getList("status");
        boolean isStatus = false;
        for (String sts : statuses) {
            if (sts.equals(status)) {
                isStatus = true;
            }
            else {
                isStatus = false;
                break;
            }
        }

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(isStatus);
    }

    @When("Find pets by status with invalid status value {string}")
    public void findPetsByStatusWithInvalidValue(String invalidStatus) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/pet/findByStatus?status=" + invalidStatus);

        Assert.assertEquals(400, response.getStatusCode());
    }

    @Then("Newly added pet ID {int} exist in query find pet by tags {string}")
    public void newlyPetIDappearsInFindPetByTags(int petId, String tag) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/pet/findByTags?tags=" + tag);

        List<Integer> ids = response.jsonPath().getList("id");
        boolean isExist = false;
        for (int id : ids) {
            if (id == petId) {
                isExist = true;
                break;
            }
        }

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(isExist);
    }

    @Then("All returned correct tag in query find pet by tags {string}")
    public void returnAllSameTagInFindPetByTags(String tag) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/pet/findByTags?tags=" + tag);

        Assert.assertEquals(200, response.getStatusCode());
    }
}
