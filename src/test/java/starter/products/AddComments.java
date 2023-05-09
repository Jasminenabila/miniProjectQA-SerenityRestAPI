package starter.products;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.Locale;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class AddComments {

    protected String url = "https://altashop-api.fly.dev/api";

    Faker faker = new Faker(new Locale("in-ID"));
    String contentComments = faker.commerce().material();

    @Step("User set api post request add comments data and token valid")
    public String setApiEndpointAddRComments(){
        return url + "/products/12372/comments";
    }

    @Step("User send post api request add comments with data token valid")
    public void sendRequestBodyAddCommentsValid(String token){
        String token_string = String.format("Bearer %s", token);
        JSONObject requestBody = new JSONObject();
        requestBody.put("content", contentComments);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddRComments());
    }

    @Step("User receive valid get response code 200 add comments data and token valid")
    public void getResponseStatusCodeAddCommentsValid(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body for add comments data and token valid")
    public void getResponseBodyAddCommentValid(){
        Response response = SerenityRest.lastResponse();
        String ID = response.getBody().jsonPath().get("data.ID").toString();
        String content = response.getBody().jsonPath().get("data.content");

        Assert.assertEquals(ID, ID);
        Assert.assertEquals(content, content);
    }

    @Step("User set api post request add comments token empty")
    public String setApiEndpointAddRCommentsTokenEmpty(){
        return url + "/products/12372/comments";
    }

    @Step("User send post api request add comments token empty")
    public void sendRequestBodyAddCommentsTokenEmpty(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("content", contentComments);

        SerenityRest.given()
                .header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddRCommentsTokenEmpty());
    }

    @Step("User receive valid get response code 401 add comments")
    public void getResponseStatusCodeAddCommentsTokenEmpty(){
        restAssuredThat(response -> response.statusCode(401));
    }

    @Step("User receive valid get response body error token empty")
    public void getResponseBodyAddCommentsTokenEmpty(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "unauthorized");
    }

    @Step("User send post api request add Comments content empty")
    public void sendRequestBodyAddCommentsContentEmpty(String token){
        String token_string = String.format("Bearer %s", token);

        JSONObject requestBody = new JSONObject();
        requestBody.put("content", "");

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddRComments());

    }

    @Step("User receive valid get response code 500 add comments")
    public void getResponseStatusCodeAddCommentsContentEmpty(){
        restAssuredThat(response -> response.statusCode(500));
    }

    @Step("User receive valid get response body error content empty")
    public void getResponseBodyCommentsContentEmpty(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");

        Assert.assertTrue(errorMsg.contains("ERROR: new row for relation"));
    }

    @Step("User set api post request add comments input string url")
    public String setApiEndpointAddCommentsUrlIdString(){
        return url + "/products/jasmine/comments";
    }

    @Step("User send post api request add comments with data input string url")
    public void sendRequestBodyAddCommentsContentIdString(String token){
        String token_string = String.format("Bearer %s", token);

        JSONObject requestBody = new JSONObject();
        requestBody.put("content", contentComments);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddCommentsUrlIdString());

    }

    @Step("User receive valid get response code 400 add comments")
    public void getResponseStatusCodeAddCommentsUrlIdString(){
        restAssuredThat(response -> response.statusCode(400));
    }

    @Step("User receive valid get response body error input string url")
    public void getResponseBodyAddCommentsIdString(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");

        Assert.assertTrue(errorMsg.contains("strconv.Atoi: parsing"));
    }
}
