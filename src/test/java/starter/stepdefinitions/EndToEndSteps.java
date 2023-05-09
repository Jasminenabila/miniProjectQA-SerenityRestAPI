package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.endtoend.EndToEnd;

public class EndToEndSteps {
    @Steps
    EndToEnd endToEnd;

    @Given("User access Auth module folders positive case has been successfully")
    public void callAuthModule(){
        endToEnd.authModule();
    }

    @When("User access Products module folders positive case has been successfully")
    public void userAccessProductsModuleFoldersPositiveCaseHasBeenSuccessfully() {
        endToEnd.productsModule();
    }

    @And("User access Products Categories module folders positive case has been successfully")
    public void userAccessProductsCategoriesModuleFoldersPositiveCaseHasBeenSuccessfully() {
        endToEnd.CategoriesModule();
    }

    @Then("User access Orders module folders positive case has been successfully")
    public void userAccessOrdersModuleFoldersPositiveCaseHasBeenSuccessfully() {
        endToEnd.OrdersModule();
    }
}
