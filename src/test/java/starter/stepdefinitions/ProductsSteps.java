package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.auth.Login;
import starter.products.*;

public class ProductsSteps {

    String token;
    int id_prod;

    @Steps
    GetProducts getProducts;

    @Steps
    AddProducts addProducts;

    @Steps
    GetProductsById getProductsById;

    @Steps
    Login login;

    @Steps
    AddRatings addRatings;

    @Steps
    DeleteProducts deleteProducts;

    @Steps
    GetRatingsById getRatingsById;

    @Steps
    AddComments addComments;

    @Steps
    GetComments getComments;

    @Given("User set api get request products alta shop valid")
    public void setAPIUrlGetProducts(){
        getProducts.setApiEndpointGetProducts();
    }


    @When("User send GET api request products with url valid")
    public void userSendGETApiRequestProductsWithUrlValid() {
        getProducts.sendRequestUrlProductsvalid();
    }


    @Then("User receive valid get response code 200 products url valid")
    public void userReceiveValidGetResponseCodeProductsUrlValid() {
        getProducts.getResponseCodeUrlValid();
    }

    @And("User receive valid get response body for products url valid")
    public void userReceiveValidGetResponseBodyForProductsUrlValid() {
        getProducts.getResponseBodyProductsValid();
    }

    @Given("User set api get request products alta shop invalid")
    public void userSetApiGetRequestProductsAltaShopInvalid() {
        getProducts.setApiEndpointProductInvalid();
    }

    @When("User send GET api request products with url invalid")
    public void userSendGETApiRequestProductsWithUrlInvalid() {
        getProducts.sendRequestBodyProductInvalid();
    }

    @Then("User receive valid get response code 404 products url invalid")
    public void userReceiveValidGetResponseCodeProductsUrlInvalid() {
        getProducts.getResponseCodeUrlInvalid();
    }

    @Given("User set api post request add products data valid")
    public void userSetApiPostRequestAddProductsDataValid() {
        addProducts.setApiEndpointAddProducts();
    }

    @When("User send post api request add products with url valid")
    public void userSendPostApiRequestAddProductsWithUrlValid() {
        addProducts.sendRequestAddProductSuccess();
    }

    @Then("User receive valid get response code 200 add products data valid")
    public void userReceiveValidGetResponseCodeAddProductsDataValid() {
        addProducts.getResponseStatusCodeAddProducts();
    }

    @And("User receive valid get response body for add products url valid")
    public void userReceiveValidGetResponseBodyForAddProductsUrlValid() {
        addProducts.getResponseBodyAddProductValid();
    }

    @When("User send post api request add products name empty")
    public void userSendPostApiRequestAddProductsNameEmpty() {
        addProducts.sendRequestAddProductNameEmpty();
    }

    @Then("User receive valid get response code 500 add products name empty")
    public void userReceiveValidGetResponseCodeAddProductsNameEmpty() {
        addProducts.getResponseCodeAddProductNameEmpty();
    }

    @And("User receive valid get response body for add products name empty")
    public void userReceiveValidGetResponseBodyForAddProductsNameEmpty() {
        addProducts.getResponseBodyAddProductNameEmpty();
    }

    @When("User send post api request add products name format integer")
    public void userSendPostApiRequestAddProductsNameFormatInteger() {
        addProducts.sendRequestAddProductNameInteger();
    }

    @Then("User receive valid get response code 400 add products invalid format")
    public void userReceiveValidGetResponseCodeAddProductsNameFormatInteger() {
        addProducts.getResponseCodeAddProductNameInteger();
    }

    @And("User receive valid get response body for add products invalid format")
    public void userReceiveValidGetResponseBodyForAddProductsNameFormatInteger() {
        addProducts.getResponseBodyAddProductNameInteger();
    }

    @When("User send post api request add products price empty")
    public void userSendPostApiRequestAddProductsPriceEmpty() {
        addProducts.sendRequestAddProductPriceEmpty();
    }

    @When("User send post api request add products price input negative")
    public void userSendPostApiRequestAddProductsPriceInputNegative() {
        addProducts.sendRequestAddProductInputPriceMinus();
    }

    @When("User send post api request add products categories empty")
    public void userSendPostApiRequestAddProductsCategoriesEmpty() {
        addProducts.sendRequestAddProductCategoriesEmpty();
    }

    @When("User send post api request add products categories input string")
    public void userSendPostApiRequestAddProductsCategoriesInputString() {
        addProducts.sendRequestAddProductCategoriesInputString();
    }

    @Given("User set api get request products by id found")
    public void userSetApiGetRequestProductsByIdFound() {
        login.setAPIEndpointLogin();
        login.sendPostLoginRequest();

        addProducts.setApiEndpointAddProducts();
        addProducts.sendRequestAddProductSuccess();
        addProducts.getResponseStatusCodeAddProducts();
        addProducts.getResponseBodyAddProductValid();

        getProducts.setApiEndpointGetProducts();
        getProducts.sendRequestUrlProductsvalid();
        String Prod_ID = getProducts.getResponseBodyProductsValid();
        id_prod = Integer.parseInt(Prod_ID);

        getProductsById.setApiEndpointGetProductIdFound(id_prod);
    }

    @When("User send GET api request products by id found")
    public void userSendGETApiRequestProductsByIdFound() {
        getProductsById.sendRequestProductIdFound(id_prod);
    }

    @Then("User receive valid get response code 200 products id found")
    public void userReceiveValidGetResponseCodeProductsIdFound() {
        getProductsById.getResponseCodeGetProdIDFound();
    }

    @And("User receive valid get response body for products id found")
    public void userReceiveValidGetResponseBodyForProductsIdFound() {
        getProductsById.getResponseBodyProdIDFound();
    }

    @Given("User set api get request products by id not found")
    public void userSetApiGetRequestProductsByIdNotFound() {
        getProductsById.setApiEndpointGetProductIDNotFound();
    }

    @When("User send GET api request products by id not found")
    public void userSendGETApiRequestProductsByIdNotFound() {
        getProductsById.sendRequestBodyGetProdIDNotFound();
    }

    @Then("User receive valid get response code 404 products id not found")
    public void userReceiveValidGetResponseCodeProductsIdNotFound() {
        getProductsById.getResponseCodeProdIDNotFound();
    }

    @And("User receive valid get response body for products id not found")
    public void userReceiveValidGetResponseBodyForProductsIdNotFound() {
        getProductsById.getResponseBodyProdIDNotFound();
    }

    @Given("User set api post request add ratings url valid")
    public void userSetApiPostRequestAddRatingsUrlValid() {
        login.setAPIEndpointLogin();
        login.sendPostLoginRequest();

        addRatings.setApiEndpointAddRatings();
    }

    @When("User send post api request add ratings with data valid")
    public void userSendPostApiRequestAddRatingsWithDataValid() {

        token = login.responseSuccessLogin();
        addRatings.sendRequestBodyAddRatingsValid(token);
    }

    @Then("User receive valid get response code 200 add ratings data valid")
    public void userReceiveValidGetResponseCodeAddRatingsDataValid() {
        addRatings.getResponseStatusCodeAddRatingsValid();
    }

    @And("User receive valid get response body for add ratings url valid")
    public void userReceiveValidGetResponseBodyForAddRatingsUrlValid() {
        addRatings.getResponseBodyAddRatingsValid();
    }

    @Given("User set api post request add ratings url not found")
    public void userSetApiPostRequestAddRatingsUrlNotFound() {
        login.setAPIEndpointLogin();
        login.sendPostLoginRequest();

        addRatings.setApiEndpointAddRatingsNotFound();
    }

    @Then("User receive valid get response code 500 add ratings data not found")
    public void userReceiveValidGetResponseCodeAddRatingsDataNotFound() {
        addRatings.getResponseStatusCodeIdNotFound();
    }

    @And("User receive valid get response body for add ratings id not found")
    public void userReceiveValidGetResponseBodyForAddRatingsIdNotFound() {
        addRatings.getResponseBodyRatingsNotFound();
    }

    @When("User send post api request add ratings with token empty")
    public void userSendPostApiRequestAddRatingsWithTokenEmpty() {
        addRatings.sendRequestRatingsTokenEmpty();
    }

    @Then("User receive valid get response code 401 token empty")
    public void userReceiveValidGetResponseCodeTokenEmpty() {
        addRatings.getResponseCodeRatingsTokenEmpty();
    }

    @And("User receive valid get response body for token empty")
    public void userReceiveValidGetResponseBodyForTokenEmpty() {
        addRatings.getResponseBodyRatingsTokenEmpty();
    }

    @When("User send post api request add ratings with data not found")
    public void userSendPostApiRequestAddRatingsWithDataNotFound() {
        token = login.responseSuccessLogin();
        addRatings.sendRequestRatingsNotFound(token);
    }

    @Given("User create valid product before")
    public void userSetApiDeleteRequestProductsByIdFound() {
        addProducts.setApiEndpointAddProducts();
        addProducts.sendRequestAddProductSuccess();
        addProducts.getResponseStatusCodeAddProducts();
    }

    @When("User send delete api request products by id found")
    public void userSendDeleteApiRequestProductsByIdFound() {
        String product_id = addProducts.getResponseBodyAddProductValid();
        deleteProducts.setApiEndpointDeleteProducts(product_id);
        deleteProducts.sendDeleteRequestIdFound(product_id);
    }

    @Then("User receive api delete valid get response code 200 products id found")
    public void userReceiveApiDeleteValidGetResponseCodeProductsIdFound() {
        deleteProducts.getResponseCodeDeleteIdFound();
    }

    @And("User receive valid api delete get response body for products id found")
    public void userReceiveValidApiDeleteGetResponseBodyForProductsIdFound() {
        deleteProducts.getResponseBodyDeleteIdFound();
    }

    @Given("User set api delete request products by id limit max")
    public void userSetApiDeleteRequestProductsByIdLimitMax() {
        deleteProducts.setApiEndpointIdLimitMax();
    }

    @When("User send delete api request products by id limit max")
    public void userSendDeleteApiRequestProductsByIdLimitMax() {
        deleteProducts.sendDeleteRequestIdLimitMax();
    }

    @Then("User receive valid api delete get response code 404 products id limit max")
    public void userReceiveValidApiDeleteGetResponseCodeProductsIdLimitMax() {
        deleteProducts.getResponseCodeIdLimitMax();
    }

    @And("User receive valid api delete get response body for products id limit max")
    public void userReceiveValidApiDeleteGetResponseBodyForProductsIdLimitMax() {
        deleteProducts.getResponseBodyIdLimitMax();
    }

    @Given("User set api get request ratings by id found")
    public void userSetApiGetRequestRatingsByIdFound() {
        getRatingsById.setApiEndpointGetRatingsIdFound();
    }

    @When("User send GET api request ratings by id found")
    public void userSendGETApiRequestRatingsByIdFound() {
        getRatingsById.sendRequestRatingsIdFound();
    }

    @Then("User receive valid get response code 200 ratings")
    public void userReceiveValidGetResponseCodeRatings() {
        getRatingsById.getResponseBodyRatings();
    }

    @And("User receive valid get response body for ratings")
    public void userReceiveValidGetResponseBodyForRatings() {
        getRatingsById.getResponseBodyRatings();
    }

    @Given("User set api get request ratings by id not found")
    public void userSetApiGetRequestRatingsByIdNotFound() {
        getRatingsById.setApiEndpointGetRatingsIdNotFound();
    }

    @When("User send GET api request ratings by id not found")
    public void userSendGETApiRequestRatingsByIdNotFound() {
        getRatingsById.sendRequestRatingsIdNotFound();
    }

    @Given("User set api get request ratings by id input string")
    public void userSetApiGetRequestRatingsByIdInputString() {
        getRatingsById.setApiEndpointGetRatingsIdStrings();
    }

    @When("User send GET api request ratings by id input string")
    public void userSendGETApiRequestRatingsByIdInputString() {
        getRatingsById.sendRequestRatingsIdStrings();
    }

    @Then("User receive valid get response code 400 ratings id input string")
    public void userReceiveValidGetResponseCodeRatingsIdInputString() {
        getRatingsById.getResponseCodeGetRatingsInputString();
    }

    @And("User receive valid get response body for ratings id input string")
    public void userReceiveValidGetResponseBodyForRatingsIdInputString() {
        getRatingsById.getResponseBodyRatingsInputString();
    }

    @Given("User set api post request add comments data and token valid")
    public void userSetApiPostRequestAddCommentsDataAndTokenValid() {
        login.setAPIEndpointLogin();
        login.sendPostLoginRequest();

        addComments.setApiEndpointAddRComments();
    }

    @When("User send post api request add comments with data token valid")
    public void userSendPostApiRequestAddCommentsWithDataTokenValid() {

        token = login.responseSuccessLogin();
        addComments.sendRequestBodyAddCommentsValid(token);
    }

    @Then("User receive valid get response code 200 add comments data and token valid")
    public void userReceiveValidGetResponseCodeAddCommentsDataAndTokenValid() {
        addComments.getResponseStatusCodeAddCommentsValid();
    }

    @And("User receive valid get response body for add comments data and token valid")
    public void userReceiveValidGetResponseBodyForAddCommentsDataAndTokenValid() {
        addComments.getResponseBodyAddCommentValid();
    }

    @Given("User set api post request add comments token empty")
    public void userSetApiPostRequestAddCommentsTokenEmpty() {
        addComments.setApiEndpointAddRCommentsTokenEmpty();
    }

    @When("User send post api request add comments token empty")
    public void userSendPostApiRequestAddCommentsTokenEmpty() {
        addComments.sendRequestBodyAddCommentsTokenEmpty();
    }

    @Then("User receive valid get response code 401 add comments")
    public void userReceiveValidGetResponseCodeAddComments() {
        addComments.getResponseStatusCodeAddCommentsTokenEmpty();
    }

    @And("User receive valid get response body error token empty")
    public void userReceiveValidGetResponseBodyErrorTokenEmpty() {
        addComments.getResponseBodyAddCommentsTokenEmpty();
    }

    @When("User send post api request add Comments content empty")
    public void userSendPostApiRequestAddCommentsContentEmpty() {
        token = login.responseSuccessLogin();
        addComments.sendRequestBodyAddCommentsContentEmpty(token);
    }

    @Then("User receive valid get response code 500 add comments")
    public void userReceiveValidGetResponseCodeAddComments400() {
        addComments.getResponseStatusCodeAddCommentsContentEmpty();
    }

    @And("User receive valid get response body error content empty")
    public void userReceiveValidGetResponseBodyErrorContentEmpty() {
        addComments.getResponseBodyCommentsContentEmpty();
    }

    @Given("User set api post request add comments input string url")
    public void userSetApiPostRequestAddCommentsInputStringUrl() {
        login.setAPIEndpointLogin();
        login.sendPostLoginRequest();

        addComments.setApiEndpointAddCommentsUrlIdString();
    }

    @When("User send post api request add comments with data input string url")
    public void userSendPostApiRequestAddCommentsWithDataInputStringUrl() {
        token = login.responseSuccessLogin();
        addComments.sendRequestBodyAddCommentsContentIdString(token);
    }

    @And("User receive valid get response body error input string url")
    public void userReceiveValidGetResponseBodyErrorInputStringUrl() {
        addComments.getResponseBodyAddCommentsIdString();
    }

    @Then("User receive valid get response code 400 add comments")
    public void userReceiveValidGetResponseCodeAddCommentsStringUrl() {
        addComments.getResponseStatusCodeAddCommentsUrlIdString();
    }

    @Given("User set api get request comments url valid")
    public void userSetApiGetRequestCommentsUrlValid() {
        getComments.setApiEndpointGetComments();
    }

    @When("User send GET api request comments with url valid")
    public void userSendGETApiRequestCommentsWithUrlValid() {
        getComments.sendRequestUrlCommentsIdFound();
    }

    @Then("User receive valid get response code 200 comments")
    public void userReceiveValidGetResponseCodeComments() {
        getComments.getResponseCodeComments200();
    }

    @And("User receive valid get response body for comments url valid")
    public void userReceiveValidGetResponseBodyForCommentsUrlValid() {
        getComments.getResponseBodyCommentsIdFound();

    }

    @Given("User set api get request comments url invalid")
    public void userSetApiGetRequestCommentsUrlInvalid() {
        getComments.setApiEndpointGetCommentsIdNotFound();
    }

    @When("User send GET api request comments with url invalid")
    public void userSendGETApiRequestCommentsWithUrlInvalid() {
        getComments.sendRequestUrlCommentsIdNotFound();
    }

    @And("User receive valid get response body for comments id or url not found")
    public void userReceiveValidGetResponseBodyForCommentsIdOrUrlNotFound() {
        getComments.getResponseBodyCommentsIdNotFound();
    }
}
