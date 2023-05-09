package starter.auth;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.junit.Assert;


import java.util.Locale;

public class Register {

    Faker faker = new Faker(new Locale("in-ID"));
    String firstName= faker.name().firstName();
    String lastName= faker.name().lastName();
    String fullname = String.format("%s %s",firstName, lastName);

    String email = String.format("%s.%s@email.com",firstName,lastName);


    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User set api POST request register")
    public String setApiEndpointRegister(){
        return url + "/auth/register";
    }

    @Step("User send request body register success")
    public void sendPostRegisterSuccess(){

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", "123password");
        requestBody.put("fullname", fullname);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointRegister());
    }

    @Step("User send request body register success with parameters")
    public void sendPostRegisterSuccessWithParam(String emailParam, String fullnameParam, String passwordParam){

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", emailParam);
        requestBody.put("password", passwordParam);
        requestBody.put("fullname", fullnameParam);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointRegister());
    }

    @Step("User receive response body success register")
    public void getResponseBodyRegisterSuccess(){
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

    @Step("User send request body register all empty")
    public void sendPostRegisterAllEmpty(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "");
        requestBody.put("password", "");
        requestBody.put("fullname", "");

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointRegister());
    }

    @Step("User receive response body error register form required")
    public void getResponseBodyFormRequired(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "email is required");
    }

    @Step("User send request body register email empty")
    public void sendPostRegisterEmailEmpty(){

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "");
        requestBody.put("password", "password123");
        requestBody.put("fullname", fullname);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointRegister());
    }

    @Step("User send request body register fullname empty")
    public void sendPostRegisterFullnameEmpty(){

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", "password123");
        requestBody.put("fullname", "");

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointRegister());
    }

    @Step("User receive response body error register form fullname required")
    public void getResponseBodyFullnameRequired(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "fullname is required");
    }

    @Step("User send request body register password empty")
    public void sendPostRegisterPasswordEmpty(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", "");
        requestBody.put("fullname", fullname);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointRegister());
    }

    @Step("User receive response body error register form password required")
    public void getResponseBodyPasswordEmpty(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertEquals(errorMsg, "password is required");
    }

    @Step("User send request body register already exist")
    public void sendPostRegisterAlreadyExist(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "QA@gmail.com");
        requestBody.put("password", "123password");
        requestBody.put("fullname", "QA Alterra");

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointRegister());
    }

    @Step("User receive response body error register already exist")
    public void getResponseBodyAlreadyExist(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertTrue(errorMsg.contains("ERROR: duplicate key value"));
    }
}
