package starter.categories;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class GetCategories {

    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User set api get request categories")
    public String setApiEndpointGetCategories(){
        return url + "/categories";
    }

    @Step("User send GET api request categories")
    public void sendRequestUrlCategoriesvalid(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetCategories());
    }

    @Step("User receive valid get response code 200 categories")
    public void getResponseCodeUrlCategoriesValid(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body data for categories")
    public String getResponseBodyCategoriesIDFound(){
        Response response = SerenityRest.lastResponse();
        String ID = response.getBody().jsonPath().get("data[0].ID").toString();
        String name = response.getBody().jsonPath().get("data[0].Name");
        String desc = response.getBody().jsonPath().get("data[0].Description");

        Assert.assertEquals(ID, ID);
        Assert.assertEquals(name, name);
        Assert.assertEquals(desc, desc);

        return ID;
    }

    @Step("User set api get request categories url invalid")
    public String setApiEndpointGetCategoriesInvalid(){
        return url + "/catego";
    }

    @Step("User send GET api request categories with url invalid")
    public void sendRequestUrlCategoriesInvalid(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetCategoriesInvalid());
    }

    @Step("User receive valid get response code 404 categories invalid")
    public void getResponseCodeUrlCategoriesInValid(){
        restAssuredThat(response -> response.statusCode(404));
    }
}
