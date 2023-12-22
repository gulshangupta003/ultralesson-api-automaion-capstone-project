package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listener.RestAssuredListener;
import models.cartItems.AddItemToCartRequestBody;
import models.cartItems.AddItemToCartResponseBody;
import utilities.EndpointConfig;

import static io.restassured.RestAssured.*;

public class CartItemClients {
    public AddItemToCartResponseBody addItemToCart(String accessToken, String cartId, String productId, int quantity) {
        String addItemToCartEndpoint = EndpointConfig.getEndpoint("cartItems", "addItemToCart").replace("{{CART_ID}}", cartId);

        AddItemToCartRequestBody addItemToCartRequestBody = AddItemToCartRequestBody.builder()
                .productId(productId)
                .quantity(quantity)
                .build();

        Response response = given()
                .filter(new RestAssuredListener())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(addItemToCartRequestBody)
                .post(addItemToCartEndpoint);

        response.then()
                .log().body();

        AddItemToCartResponseBody addItemToCartResponseBody = response.as(AddItemToCartResponseBody.class);
        addItemToCartResponseBody.setStatusCode(response.getStatusCode());

        return addItemToCartResponseBody;
    }
}
