package starter.products;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class DeleteProducts {

    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User create valid product before")
    public String setApiEndpointDeleteProducts(String id){
        return url + String.format("/products/%s",id);
    }

    @Step("User send delete api request products by id found")
    public void sendDeleteRequestIdFound(String id){
        SerenityRest.given().header("Content-Type", "application/json").delete(setApiEndpointDeleteProducts(id));
    }

    @Step("User receive api delete valid get response code 200 products id found")
    public void getResponseCodeDeleteIdFound(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid api delete get response body for products id found")
    public void getResponseBodyDeleteIdFound(){
        Response response = SerenityRest.lastResponse();
        String data = response.getBody().jsonPath().get("data");

        Assert.assertEquals(data, null);

    }

    @Step("User set api delete request products by id limit max")
    public String setApiEndpointIdLimitMax(){
        return url + "/products/0109101111133";
    }

    @Step("User send delete api request products by id limit max")
    public void sendDeleteRequestIdLimitMax(){
        SerenityRest.given().header("Content-Type", "application/json").delete(setApiEndpointIdLimitMax());
    }

    @Step("User receive valid api delete get response code 404 products id limit max")
    public void getResponseCodeIdLimitMax(){
        restAssuredThat(response -> response.statusCode(500));
    }

    @Step("User receive valid api delete get response body for products id limit max")
    public void getResponseBodyIdLimitMax(){
        Response response = SerenityRest.lastResponse();
        String data = response.getBody().jsonPath().get("error");

        Assert.assertEquals(data, "109101111133 is greater than maximum value for Int4");
    }
}
