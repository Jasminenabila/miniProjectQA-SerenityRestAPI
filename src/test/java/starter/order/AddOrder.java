package starter.order;


import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.json.JSONArray;


import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class AddOrder {

    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User create product and login before to get product id")
    public String setApiEndpointAddOrder(){
        return url + "/orders";
    }

    @Step("User send POST api request orders data valid and token valid")
    public void sendRequestBodyAddOrdersValid(String token, int id){
        String token_string = String.format("Bearer %s", token);

        JSONObject requestBody = new JSONObject();
        requestBody.put("product_id", id);
        requestBody.put("quantity", 2);
        JSONArray array = new JSONArray();
        array.put(requestBody);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").body(array.toString()).post(setApiEndpointAddOrder());
    }

    @Step("User send GET api request orders data valid and token empty")
    public void sendRequestBodyAddOrderTokenEmpty(int id){

        JSONObject requestBody = new JSONObject();
        requestBody.put("product_id", id);
        requestBody.put("quantity", 2);
        JSONArray array = new JSONArray();
        array.put(requestBody);

        SerenityRest.given()
                .header("Content-Type", "application/json").body(array.toString()).post(setApiEndpointAddOrder());


    }

    @Step("User receive valid get response code 200 orders data valid and token validd")
    public void getResponseStatusCodeAddOrdersValid(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body for orders data valid and token valid")
    public String getResponseBodyAddOrdersValid(){
        Response response = SerenityRest.lastResponse();
        String ID = response.getBody().jsonPath().get("data[0].ID").toString();
        String userId = response.getBody().jsonPath().get("data[0].User.ID").toString();
        String fullname = response.getBody().jsonPath().get("data[0].User.Fullname");
        String email = response.getBody().jsonPath().get("data[0].User.Email");
        String password = response.getBody().jsonPath().get("data[0].User.Password");
        String productQty = response.getBody().jsonPath().get("data[0].Quantity").toString();

        Assert.assertEquals(ID, ID);
        Assert.assertEquals(userId, userId);
        Assert.assertEquals(fullname, fullname);
        Assert.assertEquals(email, email);
        Assert.assertEquals(password, password);
        Assert.assertEquals(productQty, productQty);

        return ID;
    }

    @Step("User send GET api request orders data valid and token empty")
    public void sendRequestBodyAddOrdersTokenEmpty(int id){
        JSONObject requestBody = new JSONObject();
        requestBody.put("product_id", id);
        requestBody.put("quantity", 2);
        JSONArray array = new JSONArray();
        array.put(requestBody);

        SerenityRest.given()
                .header("Content-Type", "application/json").body(array.toString()).post(setApiEndpointAddOrder());
    }

    @Step("User receive valid get response code 200 orders data valid and token valid")
    public void getResponseStatusCodeAddOrdersTokenEmpty(){
        restAssuredThat(response -> response.statusCode(401));
    }

    @Step("User receive valid get response body for orders token empty")
    public void getResponseBodyAddOrdersTokenEmpty(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "unauthorized");
    }

    @Step("User send GET api request orders data product id input string")
    public void sendRequestAddOrderInputString(String token){
        String token_string = String.format("Bearer %s", token);
        JSONObject requestBody = new JSONObject();
        requestBody.put("product_id", "jasmine");
        requestBody.put("quantity", 2);
        JSONArray array = new JSONArray();
        array.put(requestBody);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").body(array.toString()).post(setApiEndpointAddOrder());
    }

    @Step("User receive valid get response code 400 product id")
    public void getResponseStatusCodeAddOrdersInputProductId(){
        restAssuredThat(response -> response.statusCode(400));
    }

    @Step("User receive valid get response body error for orders data product id")
    public void getResponseBodyOrderProductIdNegatifCase(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");

        Assert.assertTrue(errorMsg.contains("json: cannot unmarshal string into"));
    }

    @Step("User send GET api request orders data quantity input empty")
    public void sendRequestAddOrderInputQuantitytIdEmpty(String token, int id){
        String token_string = String.format("Bearer %s", token);

        JSONObject requestBody = new JSONObject();
        requestBody.put("product_id", id);
        requestBody.put("quantity", "");
        JSONArray array = new JSONArray();
        array.put(requestBody);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").body(array.toString()).post(setApiEndpointAddOrder());
    }

    @Step("User send GET api request orders data product id input empty")
    public void sendRequestAddOrderInputProductIdEmpty(String token){
        String token_string = String.format("Bearer %s", token);

        JSONObject requestBody = new JSONObject();
        requestBody.put("product_id", "");
        requestBody.put("quantity", 2);
        JSONArray array = new JSONArray();
        array.put(requestBody);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").body(array.toString()).post(setApiEndpointAddOrder());
    }

    @Step("User send GET api request orders data quantity input minus")
    public void sendRequestQuantityMinus(String token, int id){
        String token_string = String.format("Bearer %s", token);

        JSONObject requestBody = new JSONObject();
        requestBody.put("product_id", id);
        requestBody.put("quantity", -2);
        JSONArray array = new JSONArray();
        array.put(requestBody);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").body(array.toString()).post(setApiEndpointAddOrder());
    }

    @Step("User receive valid get response code 400 quantity id")
    public void getResponseStatusCodeAddOrdersQuantityNegatif(){
        restAssuredThat(response -> response.statusCode(400));
    }

    @Step("User receive valid get response body error for quantity")
    public void getResponseBodyErrorQuantity(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");

        Assert.assertTrue(errorMsg.contains("json: cannot unmarshal"));
    }
}
