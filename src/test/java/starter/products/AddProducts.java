package starter.products;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.Locale;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class AddProducts {

    protected String url = "https://altashop-api.fly.dev/api";

    Faker faker = new Faker(new Locale("in-ID"));
    String ProductName = faker.commerce().productName();
    String ProductDescription = faker.commerce().material();
    Integer ProductPrice = faker.number().numberBetween(2000,10000);

    @Step("User set api post request add products data valid")
    public String setApiEndpointAddProducts(){
        return url + "/products";
    }

    @Step("User send post api request add products with url valid")
    public void sendRequestAddProductSuccess(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", ProductName);
        requestBody.put("description", ProductDescription);
        requestBody.put("price", ProductPrice);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddProducts());
    }

    @Step("User send post api request add products with parameters")
    public void sendRequestAddProductSuccessWithParam(String productNameParam, String productDescParam, Integer productPriceParam){
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", productNameParam);
        requestBody.put("description", productDescParam);
        requestBody.put("price", productPriceParam);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddProducts());
    }

    @Step("User receive valid get response code 200 add products data valid")
    public void getResponseStatusCodeAddProducts(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("User receive valid get response body for add products url valid")
    public String getResponseBodyAddProductValid(){
        Response response = SerenityRest.lastResponse();
        String ID = response.getBody().jsonPath().get("data.ID").toString();
        Integer productID = response.getBody().jsonPath().get("data.ID");
        String name = response.getBody().jsonPath().get("data.Name");
        String desc = response.getBody().jsonPath().get("data.Description");
        String price = response.getBody().jsonPath().get("data.Price").toString();
        String ratings = response.getBody().jsonPath().get("data.Ratings").toString();

        Assert.assertEquals(ID, ID);
        Assert.assertEquals(name, name);
        Assert.assertEquals(desc, desc);
        Assert.assertEquals(price, price);
        Assert.assertEquals(ratings, ratings);
        Assert.assertEquals(productID, productID);

        return ID;
    }

    @Step("User send post api request add products name empty")
    public void sendRequestAddProductNameEmpty(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "");
        requestBody.put("description", ProductDescription);
        requestBody.put("price", ProductPrice);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddProducts());
    }

    @Step("User receive valid get response code 500 add products name empty")
    public void getResponseCodeAddProductNameEmpty(){
        restAssuredThat(response -> response.statusCode(500));
    }

    @Step("User receive valid get response body for add products name empty")
    public void getResponseBodyAddProductNameEmpty(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertTrue(errorMsg.contains("ERROR: new row for relation"));
    }

    @Step("User send post api request add products name format integer")
    public void sendRequestAddProductNameInteger(){

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", 123457);
        requestBody.put("description", ProductDescription);
        requestBody.put("price", ProductPrice);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddProducts());
    }

    @Step("User receive valid get response code 400 add products invalid format")
    public void getResponseCodeAddProductNameInteger(){
        restAssuredThat(response -> response.statusCode(400));
    }

    @Step("User receive valid get response body for add products invalid format")
    public void getResponseBodyAddProductNameInteger(){
        Response response = SerenityRest.lastResponse();
        String errorMsg = response.getBody().jsonPath().get("error");
        Assert.assertTrue(errorMsg.contains("json: cannot unmarshal"));
    }

    @Step("User send post api request add products price empty")
    public void sendRequestAddProductPriceEmpty(){

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", ProductName);
        requestBody.put("description", ProductDescription);
        requestBody.put("price", "");

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddProducts());

    }

    @Step("User send post api request add products price input negative")
    public void sendRequestAddProductInputPriceMinus(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", ProductName);
        requestBody.put("description", ProductDescription);
        requestBody.put("price", -12000);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddProducts());
    }

    @Step("User send post api request add products categories empty")
    public void sendRequestAddProductCategoriesEmpty(){
        JSONObject requestBody = new JSONObject();
        JSONObject requestBody1 = new JSONObject();
        requestBody.put("name", ProductName);
        requestBody.put("description", ProductDescription);
        requestBody.put("price", ProductPrice);
        requestBody1.put("%s","");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(requestBody1);
        requestBody.put("categories", jsonArray);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddProducts());
    }

    @Step("User send post api request add products categories input string")
    public void sendRequestAddProductCategoriesInputString(){

        JSONObject requestBody = new JSONObject();
        JSONObject requestBody1 = new JSONObject();
        requestBody.put("name", ProductName);
        requestBody.put("description", ProductDescription);
        requestBody.put("price", ProductPrice);
        requestBody1.put("%s","jasmine");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(requestBody1);
        requestBody.put("categories", jsonArray);

        SerenityRest.given().header("Content-Type", "application/json").body(requestBody.toJSONString()).post(setApiEndpointAddProducts());

    }
}
