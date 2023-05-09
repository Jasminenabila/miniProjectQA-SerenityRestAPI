package starter.order;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class GetOrderById {

    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User set api get order by id")
    public String setApiEndpointGetOrderById(int id){
        return url + String.format("/orders/%s",id);
    }

    @Step("User Get Order by id found with token valid")
    public void sendRequestGetOrdersById(String token, int id){
        String token_string = String.format("Bearer %s", token);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").get(setApiEndpointGetOrderById(id));
    }

    @Step("User receive valid get response code 200 get orders by id")
    public void getResponseCode200GetOrdersByid(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body for get orders by id")
    public void getResponseBodySuccessGetOrderById(){
        Response response = SerenityRest.lastResponse();
        String ID = response.getBody().jsonPath().get("data.ID").toString();
        String userId = response.getBody().jsonPath().get("data.User.ID").toString();
        String fullname = response.getBody().jsonPath().get("data.User.Fullname");
        String email = response.getBody().jsonPath().get("data.User.Email");
        String password = response.getBody().jsonPath().get("data.User.Password");
        String productId = response.getBody().jsonPath().get("data.Product.ID").toString();
        String productName = response.getBody().jsonPath().get("data.Product.Name");
        String productDesc = response.getBody().jsonPath().get("data.Product.Description");
        String productPrice = response.getBody().jsonPath().get("data.Product.Price").toString();
        String productRatings = response.getBody().jsonPath().get("data.Product.Ratings").toString();
        String productQty = response.getBody().jsonPath().get("data.Quantity").toString();

        Assert.assertEquals(ID, ID);
        Assert.assertEquals(userId, userId);
        Assert.assertEquals(fullname, fullname);
        Assert.assertEquals(email, email);
        Assert.assertEquals(password, password);
        Assert.assertEquals(productId, productId);
        Assert.assertEquals(productName, productName);
        Assert.assertEquals(productDesc, productDesc);
        Assert.assertEquals(productPrice, productPrice);
        Assert.assertEquals(productRatings, productRatings);
        Assert.assertEquals(productQty, productQty);
    }

    @Step("User send GET api request orders by id token empty")
    public void sendRequestGetOrdersByIdTokenEmpty(int id){
        SerenityRest.given()
                .header("Content-Type", "application/json").get(setApiEndpointGetOrderById(id));
    }

    @Step("User receive valid get response body for get orders token invalid")
    public void getResponseBodyOrdersByIdTokenEmpty(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "unauthorized");
    }

    @Step("User set api get order by id")
    public String setApiEndpointGetOrderByIdNotFound(){
        return url + "/orders/14045";
    }

    @Step("User send GET api request orders by id not found")
    public void sendRequestGetOrdersByIdNotFound(String token){
        String token_string = String.format("Bearer %s", token);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").get(setApiEndpointGetOrderByIdNotFound());
    }

    @Step("User receive valid get response code 404 orders id not found")
    public void getResponseCode400GetOrdersByIdNotFound(){
        restAssuredThat(response -> response.statusCode(404));
    }

    @Step("User receive valid get response body error for id not found get orders")
    public void getResponseBodyOrdersByIdNotFound(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "record not found");
    }
}
