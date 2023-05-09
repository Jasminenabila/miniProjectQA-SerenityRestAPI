package starter.products;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class GetProducts {

    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User set api get request products alta shop valid")
    public String setApiEndpointGetProducts(){
        return url + "/products";
    }

    @Step("User send GET api request products with url valid")
    public void sendRequestUrlProductsvalid(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetProducts());
    }

    @Step("User receive valid get response code 200 products url valid")
    public void getResponseCodeUrlValid(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body for products url valid")
    public String getResponseBodyProductsValid(){
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

        return ID;
    }

    @Step("User set api get request products alta shop invalid")
    public String setApiEndpointProductInvalid(){
        return url + "/prod";
    }

    @Step("User send GET api request products with url invalid")
    public void sendRequestBodyProductInvalid(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointProductInvalid());
    }

    @Step("User receive valid get response code 404 products url invalid")
    public void getResponseCodeUrlInvalid(){
        restAssuredThat(response -> response.statusCode(404));
    }
}
