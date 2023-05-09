package starter.order;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class GetOrder {

    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User set api get order")
    public String setApiEndpointGetOrder(){
        return url + "/orders";
    }

    @Step("User send POST api request orders data valid and token valid")
    public void sendRequestBodyGetOrderValid(String token){
        String token_string = String.format("Bearer %s", token);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").get(setApiEndpointGetOrder());
    }

    @Step("User receive valid get response code 200 token valid get orders")
    public void getResponseCode200GetOrdersValid(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body for get orders token valid")
    public void getResponseBodyOrdersValid(){
        Response response = SerenityRest.lastResponse();
        String productName = response.getBody().jsonPath().get("data[0].Product");
        String productPrice = response.getBody().jsonPath().get("data[0].Price").toString();
        String productQuantity = response.getBody().jsonPath().get("data[0].Quantity").toString();
        String productSubtotal = response.getBody().jsonPath().get("data[0].Subtotal").toString();

        Assert.assertEquals(productName, productName);
        Assert.assertEquals(productPrice, productPrice);
        Assert.assertEquals(productQuantity, productQuantity);
        Assert.assertEquals(productSubtotal, productSubtotal);

    }

    @Step("User send POST api request orders data valid and token valid")
    public void sendRequestBodyGetOrderTokenInvalid(){
        String token_string = String.format("Bearer %s", "xcncncss");

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").get(setApiEndpointGetOrder());
    }

    @Step("User receive valid get response code 404 orders token invalid")
    public void getResponseCode401TokenInvalidGetOrders(){
        restAssuredThat(response -> response.statusCode(401));
    }

    @Step("User receive valid get response body for get orders token invalid")
    public void getResponseBodyOrdersTokenInvalid(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "token contains an invalid number of segments");
    }
}
