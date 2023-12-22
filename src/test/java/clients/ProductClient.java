package clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listener.RestAssuredListener;
import models.product.GetProductsResponseBody;
import utilities.EndpointConfig;

import static io.restassured.RestAssured.given;

public class ProductClient {
    public GetProductsResponseBody getProducts(String accessToken) {
        String getProductEndpoint = EndpointConfig.getEndpoint("product", "productList");

        Response response = given()
                .filter(new RestAssuredListener())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .get(getProductEndpoint);

        GetProductsResponseBody getProductsResponseBody = new GetProductsResponseBody();
        getProductsResponseBody.setStatusCode(response.getStatusCode());
        getProductsResponseBody.setProductList(response.jsonPath().getList("", GetProductsResponseBody.Product.class));

        return getProductsResponseBody;
    }

    public GetProductsResponseBody getProducts(String accessToken, int limit, int page) {
        String getProductEndpoint = EndpointConfig.getEndpoint("product", "productList");

        Response response = given()
                .filter(new RestAssuredListener())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .queryParam("limit", limit)
                .queryParam("page", page)
                .get(getProductEndpoint);

        GetProductsResponseBody getProductsResponseBody = new GetProductsResponseBody();
        getProductsResponseBody.setStatusCode(response.getStatusCode());
        getProductsResponseBody.setProductList(response.jsonPath().getList("", GetProductsResponseBody.Product.class));

        return getProductsResponseBody;
    }
}
