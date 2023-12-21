package clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listener.RestAssuredListener;
import models.cart.CreateCartResponseBody;
import models.cart.DeleteCartResponseBody;
import models.cart.GetCartResponseBody;
import utilities.EndpointConfig;

import static io.restassured.RestAssured.given;

public class CartClient {
    public CreateCartResponseBody createCart(String accessToken) {
        String createCartEndpoint = EndpointConfig.getEndpoint("cart", "createCart");

        Response response = given()
                .filter(new RestAssuredListener())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post(createCartEndpoint);

        CreateCartResponseBody createCartResponseBody = response.as(CreateCartResponseBody.class);
        createCartResponseBody.setStatusCode(response.getStatusCode());

        return createCartResponseBody;
    }

    public GetCartResponseBody getCart(String accessToken) {
        String getCartEndpoint = EndpointConfig.getEndpoint("cart", "getCart");

        Response response = given()
                .filter(new RestAssuredListener())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(getCartEndpoint);

        GetCartResponseBody getCartResponseBody = response.as(GetCartResponseBody.class);
        getCartResponseBody.setStatusCode(response.getStatusCode());

        return getCartResponseBody;
    }

    public DeleteCartResponseBody deleteCart(String accessToken, String cartId) {
        String deleteCartEndpoint = EndpointConfig.getEndpoint("cart", "deleteCart").replace("{CART_ID}", cartId);

        Response response = given()
                .filter(new RestAssuredListener())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(deleteCartEndpoint);

        DeleteCartResponseBody deleteCartResponseBody = new DeleteCartResponseBody();
        if (response.getStatusCode() == 500)
            deleteCartResponseBody.setError(response.jsonPath().getString("error"));
        deleteCartResponseBody.setStatusCode(response.getStatusCode());

        return deleteCartResponseBody;
    }
}
