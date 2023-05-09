package starter.auth;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class Login{

    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User set api POST request login")
    public String setAPIEndpointLogin(){
        return url + "/auth/login";
    }

    @Step("User send request body login")
    public void sendPostLoginRequest(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "someone@mail.com");
        requestBody.put("password", "123123");

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setAPIEndpointLogin());
    }

    @Step("User send request body login with parameters")
    public void sendPostLoginRequestwithParam(String emailLogin, String passwordLogin){
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", emailLogin);
        requestBody.put("password", passwordLogin);
        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setAPIEndpointLogin());
    }


    @Step("User send request body login not registered")
    public void sendPostLoginRequestNotRegistered(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "jasmine@gmail.com");
        requestBody.put("password", "pass");

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setAPIEndpointLogin());
    }

    @Step("User receive response code 200")
    public void getResponseCodeSuccessLogin() {
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive response body success")
    public String responseSuccessLogin(){
        Response response = SerenityRest.lastResponse();
        String tokenLogin = response.getBody().jsonPath().get("data");
        Assert.assertTrue(tokenLogin.contains("eyJh"));
        return tokenLogin;
    }

    @Step("User send request body login failed username valid but password invalid")
    public void setRequestBodyFailedScenario2(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "someone@mail.com");
        requestBody.put("password", "123password");

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setAPIEndpointLogin());
    }

    @Step("User receive response code 400")
    public void getResponseCode400(){
        restAssuredThat(response -> response.statusCode(400));
    }

    @Step("User receive response body error record not found")
    public void getResponseBodyErrorNotFound(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "email or password is invalid");
    }

    @Step("User receive response body error not registered")
    public void getResponseBodyErrorNotRegistered(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "record not found");
    }

    @Step("User send request body login failed all empty")
    public void setRequestBodyLoginFailedAllEmpty(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "");
        requestBody.put("password", "");

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setAPIEndpointLogin());
    }

    @Step("User receive response body error email is required")
    public void receiveResponseBodyErrorRequired(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "email is required");
    }

    @Step("User send request body login failed email empty")
    public void setRequestEmailEmpty(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "");
        requestBody.put("password", "123123");

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setAPIEndpointLogin());
    }

    @Step("User send request body login failed password empty")
    public void setRequestPasswordEmpty(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "someone@mail.com");
        requestBody.put("password", "");

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setAPIEndpointLogin());
    }

    @Step("User receive response body error password is required")
    public void getResponseBodyPasswordRequired(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "password is required");
    }

}
