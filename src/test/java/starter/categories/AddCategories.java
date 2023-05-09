package starter.categories;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.Locale;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class AddCategories {

    protected String url = "https://altashop-api.fly.dev/api";

    Faker faker = new Faker(new Locale("in-ID"));
    String CategoriesName = faker.company().industry();
    String CategoriesDesc = faker.company().profession();

    @Step("User set api post request add products data valid")
    public String setApiEndpointAddCategories(){
        return url + "/categories";
    }

    @Step("User send post api request add categories data valid")
    public void sendRequestAddCategoriesSuccess(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", CategoriesName);
        requestBody.put("description", CategoriesDesc);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddCategories());
    }

    @Step("User receive valid get response code 200 add categories")
    public void getResponseStatusCodeAddCategories(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body for add categories")
    public void getResponseBodyAddCategoriesValid(){
        Response response = SerenityRest.lastResponse();
        String ID = response.getBody().jsonPath().get("data.ID").toString();
        String name = response.getBody().jsonPath().get("data.Name");
        String desc = response.getBody().jsonPath().get("data.Description");

        Assert.assertEquals(ID, ID);
        Assert.assertEquals(name, name);
        Assert.assertEquals(desc, desc);
    }

    @Step("User send post api request add categories name empty")
    public void sendRequestAddCategoriesNameEmpty(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "");
        requestBody.put("description", CategoriesDesc);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddCategories());
    }

    @Step("User receive valid get response code 500 add categories name empty")
    public void getResponseStatusCodeAddCategoriesNameEmpty(){
        restAssuredThat(response -> response.statusCode(500));
    }

    @Step("User receive valid get response body for add categories name empty")
    public void getResponseBodyAddCategoriesNameEmpty(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");

        Assert.assertTrue(errorMsg.contains("ERROR: new row for relation"));
    }

    @Step("User send post api request add categories input integer")
    public void sendRequestAddCategoriesInputInteger(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", 12345);
        requestBody.put("description", CategoriesDesc);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddCategories());
    }

    @Step("User receive valid get response code 400 add categories input integer")
    public void getResponseStatusCodeAddCategoriesInputInteger(){
        restAssuredThat(response -> response.statusCode(400));
    }

    @Step("User receive valid get response body for add categories input integer")
    public void getResponseBodyAddCategoriesInputInteger(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");

        Assert.assertTrue(errorMsg.contains("json: cannot unmarshal number into"));
    }
}
