package starter.stepdefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.auth.GetInformation;
import starter.auth.Login;
import starter.auth.Register;

public class AuthSteps {

    String token;

    @Steps
    Login login;

    @Steps
    Register register;

    @Steps
    GetInformation getInformation;

    @Given("User set api POST request login")
    public void setAPIUrlLogin(){
        login.setAPIEndpointLogin();
    }

    @When("User send request body login")
    public void userSendRequestBodyLogin() {
        login.sendPostLoginRequest();
    }

    @Then("User receive response code 200")
    public void userReceiveResponseCode() {
        login.getResponseCodeSuccessLogin();
    }


    @And("User receive response body success")
    public void userReceiveResponseBodySuccess() {
        token = login.responseSuccessLogin();
    }

    @When("User send request body login failed username valid but password invalid")
    public void userSendRequestBodyLoginFailedUsernameValidButPasswordInvalid() {
        login.setRequestBodyFailedScenario2();
    }

    @Then("User receive response code 400")
    public void userReceiveResponseCodeError() {
        login.getResponseCode400();
    }


    @And("User receive response body error record not found")
    public void userReceiveResponseBodyErrorRecordNotFound() {
        login.getResponseBodyErrorNotFound();
    }

    @When("User send request body login failed all empty")
    public void userSendRequestBodyLoginFailedAllEmpty() {
        login.setRequestBodyLoginFailedAllEmpty();
    }

    @And("User receive response body error email is required")
    public void userReceiveResponseBodyErrorEmailIsRequired() {
        login.receiveResponseBodyErrorRequired();
    }

    @When("User send request body login failed email empty")
    public void userSendRequestBodyLoginFailedEmailEmpty() {
        login.setRequestEmailEmpty();
    }

    @When("User send request body login failed password empty")
    public void userSendRequestBodyLoginFailedPasswordEmpty() {
        login.setRequestPasswordEmpty();
    }


    @And("User receive response body error password is required")
    public void userReceiveResponseBodyErrorPasswordIsRequired() {
        login.getResponseBodyPasswordRequired();
    }



    @Given("User set api POST request register")
    public void userSetApiPOSTRequestRegister() {
        register.setApiEndpointRegister();
    }

    @When("User send request body register success")
    public void userSendRequestBodyRegisterSuccess() {
        register.sendPostRegisterSuccess();
    }

    @And("User receive response body success register")
    public void userReceiveResponseBodySuccessRegister() {
        register.getResponseBodyRegisterSuccess();
    }

    @When("User send request body register all empty")
    public void userSendRequestBodyRegisterAllEmpty() {
        register.sendPostRegisterAllEmpty();
    }

    @And("User receive response body error register form required")
    public void userReceiveResponseBodyErrorRegisterFormRequired() {
        register.getResponseBodyFormRequired();
    }

    @When("User send request body register email empty")
    public void userSendRequestBodyRegisterEmailEmpty() {
        register.sendPostRegisterEmailEmpty();
    }

    @When("User send request body register fullname empty")
    public void userSendRequestBodyRegisterFullnameEmpty() {
        register.sendPostRegisterFullnameEmpty();
    }

    @And("User receive response body error register form fullname required")
    public void userReceiveResponseBodyErrorRegisterFormFullnameRequired() {
        register.getResponseBodyFullnameRequired();
    }

    @When("User send request body register password empty")
    public void userSendRequestBodyRegisterPasswordEmpty() {
        register.sendPostRegisterPasswordEmpty();
    }

    @And("User receive response body error register form password required")
    public void userReceiveResponseBodyErrorRegisterFormPasswordRequired() {
        register.getResponseBodyPasswordEmpty();
    }

    @When("User send request body register already exist")
    public void userSendRequestBodyRegisterAlreadyExist() {
        register.sendPostRegisterAlreadyExist();
    }

    @And("User receive response body error register already exist")
    public void userReceiveResponseBodyErrorRegisterAlreadyExist() {
        register.getResponseBodyAlreadyExist();
    }

    @Given("User set api Get request Get Information user")
    public void userSetApiGetRequestGetInformationUser() {
        login.setAPIEndpointLogin();
        login.sendPostLoginRequest();

        getInformation.setApiEndpointGetInfo();
    }

    @When("User send request url get information")
    public void userSendRequestUrlGetInformation() {
        token = login.responseSuccessLogin();
        getInformation.sendRequestGetInfo(token);
    }

    @And("User receive response body success get information")
    public void userReceiveResponseBodySuccessGetInformation() {
        getInformation.getResponseBodySuccessGetInfo();
    }

    @When("User send request url get information token empty")
    public void userSendRequestUrlGetInformationTokenEmpty() {
        getInformation.sendRequestGetInfoEmpty();
    }

    @And("User receive response body failed get information token empty")
    public void userReceiveResponseBodyFailedGetInformationTokenEmpty() {
        getInformation.getResponseBodyGetInfoTokenEmpty();
    }

    @When("User send request url get information token invalid")
    public void userSendRequestUrlGetInformationTokenInvalid() {
        getInformation.sendRequestGetInfoTokenInvalid();
    }

    @And("User receive response body success get information token invalid")
    public void userReceiveResponseBodySuccessGetInformationTokenInvalid() {
        getInformation.getResponseBodyGetInfoTokenInvalid();
    }

    @Then("User receive response code 401")
    public void userReceiveResponseCode401() {
        getInformation.getResponseStatusCodeUnauthorization();
    }

    @When("User send request body login not registered")
    public void userSendRequestBodyLoginNotRegistered() {
        login.sendPostLoginRequestNotRegistered();
    }

    @And("User receive response body error not registered")
    public void userReceiveResponseBodyErrorNotRegistered() {
        login.getResponseBodyErrorNotRegistered();
    }
}
