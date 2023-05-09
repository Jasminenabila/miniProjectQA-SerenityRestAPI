package starter.endtoend;

import com.github.javafaker.Faker;
import net.thucydides.core.annotations.Step;
import starter.auth.GetInformation;
import starter.auth.Login;
import starter.auth.Register;
import starter.categories.AddCategories;
import starter.categories.GetCategories;
import starter.categories.GetCategoriesById;
import starter.order.AddOrder;
import starter.order.GetOrder;
import starter.order.GetOrderById;
import starter.products.*;

import java.util.Locale;

public class EndToEnd {

    Faker faker = new Faker(new Locale("in-ID"));
    String firstName= faker.name().firstName();
    String lastName= faker.name().lastName();
    String fullname = String.format("%s %s",firstName, lastName);
    String password = faker.internet().password();
    String token = "";
    String getProdID = "";
    String ProductName = faker.commerce().productName();
    String ProductDescription = faker.commerce().material();
    Integer ProductPrice = faker.number().numberBetween(2000,10000);

    String email = String.format("%s.%s@email.com",firstName,lastName);
    protected String url = "https://altashop-api.fly.dev/api";

    @Step("User access Auth module folders positive case has been successfully")
    public void authModule(){
        //register
        Register register = new Register();
        register.setApiEndpointRegister();
        register.sendPostRegisterSuccessWithParam(email, fullname, password);
        register.getResponseBodyRegisterSuccess();

        //login
        Login login = new Login();
        login.setAPIEndpointLogin();
        login.sendPostLoginRequestwithParam(email, password);
        login.getResponseCodeSuccessLogin();
        token = login.responseSuccessLogin();

        //get information
        GetInformation getInformation = new GetInformation();
        getInformation.setApiEndpointGetInfo();
        getInformation.sendRequestGetInfo(token);
        getInformation.getResponseBodySuccessGetInfo();

    }

    @Step("User access Products module folders positive case has been successfully")
    public void productsModule(){
        //Get Products
        GetProducts getProducts = new GetProducts();
        getProducts.setApiEndpointGetProducts();
        getProducts.sendRequestUrlProductsvalid();
        getProducts.getResponseCodeUrlValid();
        getProdID = getProducts.getResponseBodyProductsValid();

        // Get Products By Id
        GetProductsById getProductsById = new GetProductsById();
        getProductsById.setApiEndpointGetProductIdFound(Integer.parseInt(getProdID.toString()));
        getProductsById.getResponseCodeGetProdIDFound();
        getProductsById.getResponseBodyProdIDFoundArray();

        // Create New Products
        AddProducts addProducts = new AddProducts();
        addProducts.setApiEndpointAddProducts();
        addProducts.sendRequestAddProductSuccessWithParam(ProductName, ProductDescription , ProductPrice);
        addProducts.getResponseStatusCodeAddProducts();
        getProdID = addProducts.getResponseBodyAddProductValid();

        // assign ratings
        AddRatings addRatings = new AddRatings();
        addRatings.setApiEndpointAddRatings();
        addRatings.sendRequestBodyAddRatingsValid(token);
        addRatings.getResponseStatusCodeAddRatingsValid();
        addRatings.getResponseBodyAddRatingsValid();

        //Get Ratings
        GetRatingsById getRatingsById = new GetRatingsById();
        getRatingsById.setApiEndpointGetRatingsIdFound();
        getRatingsById.sendRequestRatingsIdFound();
        getRatingsById.getResponseCodeGetRatings();
        getRatingsById.getResponseBodyRatings();

        // Create Comments
        AddComments addComments = new AddComments();
        addComments.setApiEndpointAddRComments();
        addComments.sendRequestBodyAddCommentsValid(token);
        addComments.getResponseStatusCodeAddCommentsValid();
        addComments.getResponseBodyAddCommentValid();

        // Get Comments
        GetComments getComments = new GetComments();
        getComments.setApiEndpointGetComments();
        getComments.sendRequestUrlCommentsIdFound();
        getComments.getResponseCodeComments200();
        getComments.getResponseBodyCommentsIdFound();
    }

    @Step("User access Products Categories module folders positive case has been successfully")
    public void CategoriesModule(){
        // Create Category
        AddCategories addCategories = new AddCategories();
        addCategories.setApiEndpointAddCategories();
        addCategories.sendRequestAddCategoriesSuccess();
        addCategories.getResponseStatusCodeAddCategories();
        addCategories.getResponseBodyAddCategoriesValid();

        // Get Category
        GetCategories getCategories = new GetCategories();
        getCategories.setApiEndpointGetCategories();
        getCategories.sendRequestUrlCategoriesvalid();
        getCategories.getResponseCodeUrlCategoriesValid();
        String getIdCategories = getCategories.getResponseBodyCategoriesIDFound();

        // Get Category by id
        GetCategoriesById getCategoriesById = new GetCategoriesById();
        getCategoriesById.setApiEndpointGetCategoriesByID(getIdCategories);
        getCategoriesById.sendRequestGetCategoriesIdFound(getIdCategories);
        getCategoriesById.getResponseCodeUrlCategoriesByIdFound();
        getCategoriesById.getResponseBodyCategoriesByIDFound();

    }

    @Step("User access Orders module folders positive case has been successfully")
    public void OrdersModule(){
        // Create Order
        AddOrder addOrder = new AddOrder();
        addOrder.setApiEndpointAddOrder();
        addOrder.sendRequestBodyAddOrdersValid(token, Integer.parseInt(getProdID.toString()));
        addOrder.getResponseStatusCodeAddOrdersValid();
        String getOrderId = addOrder.getResponseBodyAddOrdersValid();

        // Get Order
        GetOrder getOrder = new GetOrder();
        getOrder.setApiEndpointGetOrder();
        getOrder.sendRequestBodyGetOrderValid(token);
        getOrder.getResponseCode200GetOrdersValid();
        getOrder.getResponseBodyOrdersValid();

        // Get Order by id
        GetOrderById getOrderById = new GetOrderById();
        getOrderById.setApiEndpointGetOrderById(Integer.parseInt(getProdID.toString()));
        getOrderById.sendRequestGetOrdersById(token, Integer.parseInt(getOrderId));
        getOrderById.getResponseCode200GetOrdersByid();
        getOrderById.getResponseBodySuccessGetOrderById();
    }
}
