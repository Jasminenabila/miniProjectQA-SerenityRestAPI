package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.auth.Login;
import starter.order.AddOrder;
import starter.order.GetOrder;
import starter.order.GetOrderById;
import starter.products.AddProducts;

public class OrderSteps {

    String token;
    int prod_id;
    int order_id;
    @Steps
    AddOrder addOrder;

    @Steps
    AddProducts addProducts;

    @Steps
    Login login;

    @Steps
    GetOrder getOrder;

    @Steps
    GetOrderById getOrderById;

    @Given("User create product and login before to get product id")
    public void userSetApiAddOrderValid(){
        login.setAPIEndpointLogin();
        login.sendPostLoginRequest();
        token = login.responseSuccessLogin();
    }

    @When("User send POST api request orders data valid and token valid")
    public void userSendPOSTApiRequestOrdersDataValidAndTokenValid() {
        addProducts.setApiEndpointAddProducts();
        addProducts.sendRequestAddProductSuccess();
        addProducts.getResponseStatusCodeAddProducts();

        String ID = addProducts.getResponseBodyAddProductValid();
        int prod_id = Integer.parseInt(ID);
        addOrder.setApiEndpointAddOrder();
        addOrder.sendRequestBodyAddOrdersValid(token, prod_id);
    }

    @Then("User receive valid get response code 200 orders data valid and token valid")
    public void userReceiveValidGetResponseCodeOrdersDataValidAndTokenValid() {
        addOrder.getResponseStatusCodeAddOrdersValid();
    }

    @And("User receive valid get response body for orders data valid and token valid")
    public void userReceiveValidGetResponseBodyForOrdersDataValidAndTokenValid() {
        addOrder.getResponseBodyAddOrdersValid();
    }

    @When("User send POST api request orders data valid and token empty")
    public void userSendGETApiRequestOrdersDataValidAndTokenEmpty() {
        addProducts.setApiEndpointAddProducts();
        addProducts.sendRequestAddProductSuccess();
        addProducts.getResponseStatusCodeAddProducts();

        String ID = addProducts.getResponseBodyAddProductValid();
        int prod_id = Integer.parseInt(ID);
        addOrder.setApiEndpointAddOrder();
        addOrder.sendRequestBodyAddOrdersTokenEmpty(prod_id);
    }

    @Then("User receive valid get response code 401 orders token empty")
    public void userReceiveValidGetResponseCodeOrdersTokenEmpty() {
        addOrder.getResponseStatusCodeAddOrdersTokenEmpty();
    }

    @And("User receive valid get response body for orders token empty")
    public void userReceiveValidGetResponseBodyForOrdersTokenEmpty() {
        addOrder.getResponseBodyAddOrdersTokenEmpty();
    }

    @When("User send POST api request orders data product id input string")
    public void userSendGETApiRequestOrdersDataProductIdInputString() {
        addProducts.setApiEndpointAddProducts();
        addProducts.sendRequestAddProductSuccess();
        addProducts.getResponseStatusCodeAddProducts();

        addOrder.setApiEndpointAddOrder();
        addOrder.sendRequestAddOrderInputString(token);
    }

    @Then("User receive valid get response code 400 product id")
    public void userReceiveValidGetResponseCodeProductId() {
        addOrder.getResponseStatusCodeAddOrdersInputProductId();
    }

    @And("User receive valid get response body error for orders data product id")
    public void userReceiveValidGetResponseBodyErrorForOrdersDataProductId() {
        addOrder.getResponseBodyOrderProductIdNegatifCase();
    }

    @When("User send POST api request orders data product id input empty")
    public void userSendGETApiRequestOrdersDataProductIdInputEmpty() {
        addProducts.setApiEndpointAddProducts();
        addProducts.sendRequestAddProductSuccess();
        addProducts.getResponseStatusCodeAddProducts();

        addOrder.setApiEndpointAddOrder();
        addOrder.sendRequestAddOrderInputProductIdEmpty(token);
    }

    @When("User send POST api request orders data quantity input minus")
    public void userSendGETApiRequestOrdersDataQuantityInputMinus() {
        addProducts.setApiEndpointAddProducts();
        addProducts.sendRequestAddProductSuccess();
        addProducts.getResponseStatusCodeAddProducts();

        String ID = addProducts.getResponseBodyAddProductValid();
        int prod_id = Integer.parseInt(ID);
        addOrder.setApiEndpointAddOrder();
        addOrder.sendRequestQuantityMinus(token,prod_id);
    }

    @Then("User receive valid get response code 400 quantity")
    public void userReceiveValidGetResponseCodeQuantity() {
        addOrder.getResponseStatusCodeAddOrdersQuantityNegatif();
    }

    @And("User receive valid get response body error for quantity")
    public void userReceiveValidGetResponseBodyErrorForQuantity() {
        addOrder.getResponseBodyErrorQuantity();
    }

    @When("User send POST api request orders data quantity input empty")
    public void userSendGETApiRequestOrdersDataQuantityInputEmpty() {
        addProducts.setApiEndpointAddProducts();
        addProducts.sendRequestAddProductSuccess();
        addProducts.getResponseStatusCodeAddProducts();

        String ID = addProducts.getResponseBodyAddProductValid();
        int prod_id = Integer.parseInt(ID);
        addOrder.setApiEndpointAddOrder();
        addOrder.sendRequestAddOrderInputQuantitytIdEmpty(token, prod_id);
    }

    @When("User send GET api request order token valid")
    public void userSendGETApiRequestOrderTokenValid() {
        getOrder.setApiEndpointGetOrder();
        getOrder.sendRequestBodyGetOrderValid(token);
    }

    @Then("User receive valid get response code 200 token valid get orders")
    public void userReceiveValidGetResponseCodeTokenValidGetOrders() {
        getOrder.getResponseCode200GetOrdersValid();
    }

    @And("User receive valid get response body for get orders token valid")
    public void userReceiveValidGetResponseBodyForGetOrdersTokenValid() {
        getOrder.getResponseBodyOrdersValid();
    }

    @When("User send GET api request orders with token invalid")
    public void userSendGETApiRequestOrdersWithTokenInvalid() {
        getOrder.sendRequestBodyGetOrderTokenInvalid();
    }

    @Then("User receive valid get response code 401 orders token invalid")
    public void userReceiveValidGetResponseCodeOrdersTokenInvalid() {
        getOrder.getResponseCode401TokenInvalidGetOrders();
    }

    @And("User receive valid get response body for get orders token invalid")
    public void userReceiveValidGetResponseBodyForGetOrdersTokenInvalid() {
        getOrder.getResponseBodyOrdersTokenInvalid();
    }

    @Given("User create orders and login before to get order id")
    public void userCreateOrdersAndLoginBeforeToGetOrderId() {
        userSetApiAddOrderValid();

        addProducts.setApiEndpointAddProducts();
        addProducts.sendRequestAddProductSuccess();
        addProducts.getResponseStatusCodeAddProducts();

        String ID = addProducts.getResponseBodyAddProductValid();
        int prod_id = Integer.parseInt(ID);

        addOrder.setApiEndpointAddOrder();
        addOrder.sendRequestBodyAddOrdersValid(token, prod_id);
        String Order_id = addOrder.getResponseBodyAddOrdersValid();
        order_id = Integer.parseInt(Order_id);
    }

    @When("User Get Order by id found with token valid")
    public void userGetOrderByIdFoundWithTokenValid() {
        getOrderById.setApiEndpointGetOrderById(order_id);
        getOrderById.sendRequestGetOrdersById(token,order_id);
    }

    @Then("User receive valid get response code 200 get orders by id")
    public void userReceiveValidGetResponseCodeGetOrdersById() {
        getOrderById.getResponseCode200GetOrdersByid();
    }

    @And("User receive valid get response body for get orders by id")
    public void userReceiveValidGetResponseBodyForGetOrdersById() {
        getOrderById.getResponseBodySuccessGetOrderById();
    }

    @Given("User send GET api request orders by id token empty")
    public void userSendGETApiRequestOrdersByIdTokenEmpty() {
        userSetApiAddOrderValid();

        addProducts.setApiEndpointAddProducts();
        addProducts.sendRequestAddProductSuccess();
        addProducts.getResponseStatusCodeAddProducts();

        String ID = addProducts.getResponseBodyAddProductValid();
        int prod_id = Integer.parseInt(ID);

        addOrder.setApiEndpointAddOrder();
        addOrder.sendRequestBodyAddOrdersValid(token, prod_id);
        String Order_id = addOrder.getResponseBodyAddOrdersValid();
        order_id = Integer.parseInt(Order_id);

        getOrderById.sendRequestGetOrdersByIdTokenEmpty(order_id);

    }

    @Then("User receive valid get response body error for token empty")
    public void userReceiveValidGetResponseBodyErrorForTokenEmpty() {
        getOrderById.getResponseBodyOrdersByIdTokenEmpty();
    }

    @Given("User set api url orders by id not found")
    public void userSetApiUrlOrdersByIdNotFound() {
        userSetApiAddOrderValid();

        getOrderById.setApiEndpointGetOrderByIdNotFound();
    }

    @When("User send GET api request orders by id not found")
    public void userSendGETApiRequestOrdersByIdNotFound() {
        getOrderById.sendRequestGetOrdersByIdNotFound(token);
    }

    @And("User receive valid get response code 404 orders id not found")
    public void userReceiveValidGetResponseCodeOrdersIdNotFound() {
        getOrderById.getResponseCode400GetOrdersByIdNotFound();
    }

    @Then("User receive valid get response body error for id not found get orders")
    public void userReceiveValidGetResponseBodyErrorForIdNotFoundGetOrders() {
        getOrderById.getResponseBodyOrdersByIdNotFound();
    }
}
