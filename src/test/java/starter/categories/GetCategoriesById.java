package starter.categories;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class GetCategoriesById {

    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User set api get request categories by id found")
    public String setApiEndpointGetCategoriesByID(String id){
        return url + String.format("/categories/%s",id);
    }

    @Step("User send GET api request categories by id found")
    public void sendRequestGetCategoriesIdFound(String id){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetCategoriesByID(id));
    }

    @Step("User receive valid get response code 200 categories by id found")
    public void getResponseCodeUrlCategoriesByIdFound(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body data for categories by id found")
    public void getResponseBodyCategoriesByIDFound(){
        Response response = SerenityRest.lastResponse();
        String ID = response.getBody().jsonPath().get("data.ID").toString();
        String name = response.getBody().jsonPath().get("data.Name");
        String desc = response.getBody().jsonPath().get("data.Description");

        Assert.assertEquals(ID, ID);
        Assert.assertEquals(name, name);
        Assert.assertEquals(desc, desc);
    }

    @Step("User set api get request categories by id not found")
    public String setApiEndpointGetCategoriesByIDNotFound(){
        return url + "/categories/90000";
    }

    @Step("User send GET api request categories by id not found")
    public void sendRequestGetCategoriesIdNotFound(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetCategoriesByIDNotFound());
    }

    @Step("User receive valid get response code 404 categories by id not found")
    public void getResponseCodeUrlCategoriesByIdNotFound(){
        restAssuredThat(response -> response.statusCode(404));
    }

    @Step("User receive valid get response body data for categories by id not found")
    public void getResponseBodyCategoriesIDNotFound(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "record not found");
    }

    @Step("User set api get request categories by id input string")
    public String setApiEndpointGetCategoriesInputString(){
        return url + "/categories/jasmine";
    }

    @Step("User send GET api request categories by id input string")
    public void sendRequestGetCategoriesInputString(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetCategoriesInputString());
    }

    @Step("User receive valid get response code 400 categories by id input string")
    public void getResponseCodeUrlCategoriesByIdInputString(){
        restAssuredThat(response -> response.statusCode(400));
    }

    @Step("User receive valid get response body data for categories by id input string")
    public void getResponseBodyCategoriesInputString(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");

        Assert.assertTrue(errorMsg.contains("strconv.Atoi: parsing"));
    }
}
