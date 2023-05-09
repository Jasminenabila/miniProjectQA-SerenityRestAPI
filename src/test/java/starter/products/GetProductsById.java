package starter.products;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class GetProductsById {

    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User set api get request products by id found")
    public String setApiEndpointGetProductIdFound(int id){
        return url + String.format("/products/%s",id);
    }

    @Step("User send GET api request products by id found")
    public void sendRequestProductIdFound(int id){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetProductIdFound(id));
    }

    @Step("User receive valid get response code 200 products id found")
    public void getResponseCodeGetProdIDFound(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body for products id found")
    public void getResponseBodyProdIDFound(){
        Response response = SerenityRest.lastResponse();
        String ID = response.getBody().jsonPath().get("data.ID").toString();
        String name = response.getBody().jsonPath().get("data.Name");
        String desc = response.getBody().jsonPath().get("data.Description");
        String price = response.getBody().jsonPath().get("data.Price").toString();
        String ratings = response.getBody().jsonPath().get("data.Ratings").toString();

        Assert.assertEquals(ID, ID);
        Assert.assertEquals(name, name);
        Assert.assertEquals(desc, desc);
        Assert.assertEquals(price, price);
        Assert.assertEquals(ratings, ratings);
    }

    @Step("User receive valid get response body for products id found Array")
    public void getResponseBodyProdIDFoundArray(){
        Response response = SerenityRest.lastResponse();
        String ID = response.getBody().jsonPath().get("data[0].ID").toString();
        String name = response.getBody().jsonPath().get("data[0].Name");
        String desc = response.getBody().jsonPath().get("data[0].Description");
        String price = response.getBody().jsonPath().get("data[0].Price").toString();
        String ratings = response.getBody().jsonPath().get("data[0].Ratings").toString();

        Assert.assertEquals(ID, ID);
        Assert.assertEquals(name, name);
        Assert.assertEquals(desc, desc);
        Assert.assertEquals(price, price);
        Assert.assertEquals(ratings, ratings);
    }

    @Step("User set api get request products by id not found")
    public String setApiEndpointGetProductIDNotFound(){
        return url + "/products/09101998";
    }

    @Step("User send GET api request products by id not found")
    public void sendRequestBodyGetProdIDNotFound(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetProductIDNotFound());
    }

    @Step("User receive valid get response code 404 products id not found")
    public void getResponseCodeProdIDNotFound(){
        restAssuredThat(response -> response.statusCode(404));
    }

    @Step("User receive valid get response body for products id not found")
    public void getResponseBodyProdIDNotFound(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "record not found");
    }
}
