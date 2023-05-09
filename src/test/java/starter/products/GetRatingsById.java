package starter.products;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class GetRatingsById {
    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User set api get request ratings by id found")
    public String setApiEndpointGetRatingsIdFound(){
        return url + "/products/13551/ratings";
    }

    @Step("User send GET api request ratings by id found")
    public void sendRequestRatingsIdFound(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetRatingsIdFound());
    }

    @Step("User receive valid get response code 200 ratings")
    public void getResponseCodeGetRatings(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body for ratings")
    public void getResponseBodyRatings(){
        Response response = SerenityRest.lastResponse();
        String dataRatings = response.getBody().jsonPath().get("data").toString();

        Assert.assertEquals(dataRatings, dataRatings);
    }

    @Step("User set api get request ratings by id not found")
    public String setApiEndpointGetRatingsIdNotFound(){
        return url + "/products/12345/ratings";
    }

    @Step("User send GET api request ratings by id not found")
    public void sendRequestRatingsIdNotFound(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetRatingsIdNotFound());
    }

    @Step("User set api get request ratings by id input string")
    public String setApiEndpointGetRatingsIdStrings(){
        return url + "/products/jasmine/ratings";
    }

    @Step("User send GET api request ratings by id input string")
    public void sendRequestRatingsIdStrings(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetRatingsIdStrings());
    }

    @Step("User receive valid get response code 400 ratings id input string")
    public void getResponseCodeGetRatingsInputString(){
        restAssuredThat(response -> response.statusCode(400));
    }

    @Step("User receive valid get response body for ratings id input string")
    public void getResponseBodyRatingsInputString(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");

        Assert.assertTrue(errorMsg.contains("strconv.Atoi: parsing"));
    }
}
