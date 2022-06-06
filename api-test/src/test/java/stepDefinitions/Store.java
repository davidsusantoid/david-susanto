package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class Store {

    private static final String BASE_URL = "https://petstore3.swagger.io/api/v3";

    private static Response response;

    @When("Add new order ID {int}")
    public void addNewOrderById(int orderId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        String reqBody = "{\"id\":" + orderId + "," +
            "\"petId\":5586," +
            "\"quantity\":7," +
            "\"shipDate\":\"2022-06-06T07:02:27.752Z\"," +
            "\"status\":\"approved\"," +
            "\"complete\":true}";
        response = requestSpecification.body(reqBody).post("/store/order");

        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("Add new order by ID with invalid input {string}")
    public void addNewOrderByIdWithInvalidInput(String orderId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        String reqBody = "{\"id\":\"" + orderId + "\"," +
                "\"petId\":5586," +
                "\"quantity\":7," +
                "\"shipDate\":\"2022-06-06T07:02:27.752Z\"," +
                "\"status\":\"approved\"," +
                "\"complete\":true}";
        response = requestSpecification.body(reqBody).post("/store/order");

        Assert.assertEquals(400, response.getStatusCode());
    }



    @Given("If order ID {int} is exist, then delete it")
    public void deleteOrderByIdIfExist(int orderId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/store/order/" + orderId);

        if (response.getStatusCode() == 200) {
            requestSpecification.delete("/store/order/" + orderId);
        }

        response = requestSpecification.get("/store/order/" + orderId);
        Assert.assertEquals(404, response.getStatusCode());
    }

    @When("Delete order ID {int}")
    public void deleteOrderByID(int orderId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.delete("/store/order/" + orderId);

        String jsonString = response.asString();
        Assert.assertEquals(200, response.getStatusCode());
    }



    @Then("Order ID {int} is found")
    public void foundOrderByID(int orderId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/store/order/" + orderId);

        String jsonString = response.asString();
        int id = JsonPath.from(jsonString).get("id");

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(orderId, id);
    }

    @Then("Order ID {int} is not found")
    public void orderByIDisNotFound(int orderId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/store/order/" + orderId);

        String jsonString = response.asString();
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("Order not found", jsonString);
    }

    @Then("Invalid order ID {string} supplied")
    public void getOrderIDbyInvalidInput(String invalidOrderId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.get("/store/order/" + invalidOrderId);

        String jsonString = response.asString();
        Assert.assertEquals(400, response.getStatusCode());
    }
}
