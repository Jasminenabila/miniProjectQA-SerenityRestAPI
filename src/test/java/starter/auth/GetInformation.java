package starter.auth;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class GetInformation {

    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User set api Get request Get Information user")
    public String setApiEndpointGetInfo(){
        return url + "/auth/info";
    }

    @Step("User send request url get information")
    public void sendRequestGetInfo(String token){
        String token_string = String.format("Bearer %s", token);

        SerenityRest.given().header("Authorization", token_string)
                .header("Content-Type", "application/json").get(setApiEndpointGetInfo());

    }

    @Step("User receive response body success get information")
    public void getResponseBodySuccessGetInfo(){
        Response response = SerenityRest.lastResponse();
        String ID = response.getBody().jsonPath().get("data.ID").toString();
        String fullname = response.getBody().jsonPath().get("data.Fullname");
        String email = response.getBody().jsonPath().get("data.Email");
        String password = response.getBody().jsonPath().get("data.Password");

        Assert.assertEquals(ID, ID);
        Assert.assertEquals(fullname, fullname);
        Assert.assertEquals(email, email);
        Assert.assertEquals(password, password);
    }

    @Step("User send request url get information token empty")
    public void sendRequestGetInfoEmpty(){
        SerenityRest.given().header("Authorization", "")
                .header("Content-Type", "application/json").get(setApiEndpointGetInfo());
    }

    @Step("User receive response body failed get information token empty")
    public void getResponseBodyGetInfoTokenEmpty(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "unauthorized");
    }

    @Step("User send request url get information token invalid")
    public void sendRequestGetInfoTokenInvalid(){
        SerenityRest.given().header("Authorization", "Bearer xxxxxx")
                .header("Content-Type", "application/json").get(setApiEndpointGetInfo());
    }

    @Step("User receive response body success get information token invalid")
    public void getResponseBodyGetInfoTokenInvalid(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "token contains an invalid number of segments");
    }

    @Step("User receive response code 401")
    public void getResponseStatusCodeUnauthorization(){
        restAssuredThat(response -> response.statusCode(401));
    }
}
