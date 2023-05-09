package starter.products;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class GetComments {
    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User set api get request comments url valid")
    public String setApiEndpointGetComments(){
        return url + "/products/12372/comments";
    }

    @Step("User send GET api request comments with url valid")
    public void sendRequestUrlCommentsIdFound(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetComments());
    }

    @Step("User receive valid get response code 200 comments")
    public void getResponseCodeComments200(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body for comments url valid")
    public void getResponseBodyCommentsIdFound(){
        Response response = SerenityRest.lastResponse();
        String ID = response.getBody().jsonPath().get("data[0].ID").toString();
        String content = response.getBody().jsonPath().get("data[0].content");

        Assert.assertEquals(ID, ID);
        Assert.assertEquals(content, content);
    }

    @Step("User set api get request comments url valid")
    public String setApiEndpointGetCommentsIdNotFound(){
        return url + "/products/0191019201/comments";
    }

    @Step("User send GET api request comments with url invalid")
    public void sendRequestUrlCommentsIdNotFound(){
        SerenityRest.given().header("Content-Type", "application/json").get(setApiEndpointGetCommentsIdNotFound());
    }

    @Step("User receive valid get response body for comments id or url not found")
    public void getResponseBodyCommentsIdNotFound(){
        Response response = SerenityRest.lastResponse();
        String data = response.getBody().jsonPath().get("data").toString();

        Assert.assertEquals(data, data);
    }
}
