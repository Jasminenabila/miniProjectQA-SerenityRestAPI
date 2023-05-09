package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.categories.AddCategories;
import starter.categories.GetCategories;
import starter.categories.GetCategoriesById;

public class CategoriesSteps {

    @Steps
    AddCategories addCategories;

    @Steps
    GetCategories getCategories;

    @Steps
    GetCategoriesById getCategoriesById;

    @Given("User set api post request add categories data valid")
    public void setAPIUrlGetProducts(){
        addCategories.setApiEndpointAddCategories();
    }

    @When("User send post api request add categories data valid")
    public void userSendPostApiRequestAddCategoriesDataValid() {
        addCategories.sendRequestAddCategoriesSuccess();
    }

    @Then("User receive valid get response code 200 add categories")
    public void userReceiveValidGetResponseCodeAddCategories() {
        addCategories.getResponseStatusCodeAddCategories();
    }

    @And("User receive valid get response body for add categories")
    public void userReceiveValidGetResponseBodyForAddCategories() {
        addCategories.getResponseBodyAddCategoriesValid();
    }

    @When("User send post api request add categories name empty")
    public void userSendPostApiRequestAddCategoriesNameEmpty() {
        addCategories.sendRequestAddCategoriesNameEmpty();
    }

    @Then("User receive valid get response code 500 add categories name empty")
    public void userReceiveValidGetResponseCodeAddCategoriesNameEmpty() {
        addCategories.getResponseStatusCodeAddCategoriesNameEmpty();
    }

    @And("User receive valid get response body for add categories name empty")
    public void userReceiveValidGetResponseBodyForAddCategoriesNameEmpty() {
        addCategories.getResponseBodyAddCategoriesNameEmpty();
    }

    @When("User send post api request add categories input integer")
    public void userSendPostApiRequestAddCategoriesInputInteger() {
        addCategories.sendRequestAddCategoriesInputInteger();
    }

    @Then("User receive valid get response code 400 add categories input integer")
    public void userReceiveValidGetResponseCodeAddCategoriesInputInteger() {
        addCategories.getResponseStatusCodeAddCategoriesInputInteger();
    }

    @And("User receive valid get response body for add categories input integer")
    public void userReceiveValidGetResponseBodyForAddCategoriesInputInteger() {
        addCategories.getResponseBodyAddCategoriesInputInteger();
    }

    @Given("User set api get request categories")
    public void userSetApiGetRequestCategories() {
        getCategories.setApiEndpointGetCategories();
    }

    @When("User send GET api request categories")
    public void userSendGETApiRequestCategories() {
        getCategories.sendRequestUrlCategoriesvalid();
    }

    @Then("User receive valid get response code 200 categories")
    public void userReceiveValidGetResponseCodeCategories() {
        getCategories.getResponseCodeUrlCategoriesValid();
    }

    @And("User receive valid get response body data for categories")
    public void userReceiveValidGetResponseBodyDataForCategories() {
        getCategories.getResponseBodyCategoriesIDFound();
    }

    @Given("User set api get request categories url invalid")
    public void userSetApiGetRequestCategoriesUrlInvalid() {
        getCategories.setApiEndpointGetCategoriesInvalid();
    }

    @When("User send GET api request categories with url invalid")
    public void userSendGETApiRequestCategoriesWithUrlInvalid() {
        getCategories.sendRequestUrlCategoriesInvalid();
    }

    @Then("User receive valid get response code 404 categories invalid")
    public void userReceiveValidGetResponseCodeCategoriesInvalid() {
        getCategories.getResponseCodeUrlCategoriesInValid();
    }

    @Given("User set api get request categories by id found")
    public void userSetApiGetRequestCategoriesByIdFound() {
        getCategories.setApiEndpointGetCategories();
        getCategories.sendRequestUrlCategoriesvalid();
        getCategories.getResponseCodeUrlCategoriesValid();
    }

    @When("User send GET api request categories by id found")
    public void userSendGETApiRequestCategoriesByIdFound() {
        String categories_id = getCategories.getResponseBodyCategoriesIDFound();

        getCategoriesById.setApiEndpointGetCategoriesByID(categories_id);
        getCategoriesById.sendRequestGetCategoriesIdFound(categories_id);
    }

    @Then("User receive valid get response code 200 categories by id found")
    public void userReceiveValidGetResponseCodeCategoriesByIdFound() {
        getCategoriesById.getResponseCodeUrlCategoriesByIdFound();
    }

    @And("User receive valid get response body data for categories by id found")
    public void userReceiveValidGetResponseBodyDataForCategoriesByIdFound() {
        getCategoriesById.getResponseBodyCategoriesByIDFound();
    }

    @Given("User set api get request categories by id not found")
    public void userSetApiGetRequestCategoriesByIdNotFound() {
        getCategoriesById.setApiEndpointGetCategoriesByIDNotFound();
    }

    @When("User send GET api request categories by id not found")
    public void userSendGETApiRequestCategoriesByIdNotFound() {
        getCategoriesById.sendRequestGetCategoriesIdNotFound();
    }

    @Then("User receive valid get response code 404 categories by id not found")
    public void userReceiveValidGetResponseCodeCategoriesByIdNotFound() {
        getCategoriesById.getResponseCodeUrlCategoriesByIdNotFound();
    }

    @And("User receive valid get response body data for categories by id not found")
    public void userReceiveValidGetResponseBodyDataForCategoriesByIdNotFound() {
        getCategoriesById.getResponseBodyCategoriesIDNotFound();
    }

    @Given("User set api get request categories by id input string")
    public void userSetApiGetRequestCategoriesByIdInputString() {
        getCategoriesById.setApiEndpointGetCategoriesInputString();
    }

    @When("User send GET api request categories by id input string")
    public void userSendGETApiRequestCategoriesByIdInputString() {
        getCategoriesById.sendRequestGetCategoriesInputString();
    }

    @Then("User receive valid get response code 400 categories by id input string")
    public void userReceiveValidGetResponseCodeCategoriesByIdInputString() {
        getCategoriesById.getResponseCodeUrlCategoriesByIdInputString();
    }

    @And("User receive valid get response body data for categories by id input string")
    public void userReceiveValidGetResponseBodyDataForCategoriesByIdInputString() {
        getCategoriesById.getResponseBodyCategoriesInputString();
    }
}
