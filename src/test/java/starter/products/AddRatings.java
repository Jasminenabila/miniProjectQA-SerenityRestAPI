package starter.products;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.Locale;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class AddRatings {

    protected String url = "https://altashop-api.fly.dev/api";

    Faker faker = new Faker(new Locale("in-ID"));
    Integer ProductRating = faker.number().numberBetween(1,5);

    @Step("User set api post request add ratings url valid")
    public String setApiEndpointAddRatings(){
        return url + String.format("/products/13551/ratings");
    }

    @Step("User send post api request add ratings with data valid")
    public void sendRequestBodyAddRatingsValid(String token){
        String token_string = String.format("Bearer %s", token);

        JSONObject requestBody = new JSONObject();
        requestBody.put("count", ProductRating);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddRatings());


    }

    @Step("User receive valid get response code 200 add ratings data valid")
    public void getResponseStatusCodeAddRatingsValid(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body for add ratings url valid")
    public void getResponseBodyAddRatingsValid(){
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

    @Step("User set api post request add ratings url not found")
    public String setApiEndpointAddRatingsNotFound(){
        return url + "/products/5000000/ratings";
    }

    @Step("User receive valid get response code 500 add ratings data not found")
    public void getResponseStatusCodeIdNotFound(){
        restAssuredThat(response -> response.statusCode(500));
    }

    @Step("User receive valid get response body for add ratings id not found")
    public void getResponseBodyRatingsNotFound(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "record not found");
    }

    @Step("User send post api request add ratings with token empty")
    public void sendRequestRatingsTokenEmpty(){
        SerenityRest.given()
                .header("Content-Type", "application/json").post(setApiEndpointAddRatings());

        JSONObject requestBody = new JSONObject();
        requestBody.put("count", ProductRating);
    }

    @Step("User receive valid get response code 401 token empty")
    public void getResponseCodeRatingsTokenEmpty(){
        restAssuredThat(response -> response.statusCode(401));
    }

    @Step("User receive valid get response body for token empty")
    public void getResponseBodyRatingsTokenEmpty(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "unauthorized");
    }

    @Step("User send post api request add ratings with data not found")
    public void sendRequestRatingsNotFound(String token){
        String token_string = String.format("Bearer %s", token);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").post(setApiEndpointAddRatingsNotFound());

        JSONObject requestBody = new JSONObject();
        requestBody.put("count", ProductRating);
    }
}
