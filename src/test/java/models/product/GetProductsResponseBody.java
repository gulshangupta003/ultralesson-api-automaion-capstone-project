package models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetProductsResponseBody {
    private int statusCode;
    private List<Product> productList;

    public Product getProductDetails(String productName) {
        if (productList != null) {
            return productList.stream()
                    .filter(product -> product.getName().equals(productName))
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Product {
        private int quantity;
        @JsonProperty("category_id")
        private String categoryId;
        private Object price;
        private String name;
        @JsonProperty("created_at")
        private String createdAt;
        private String description;
        private String id;
    }

    public void assertGetProductsResponseBody(GetProductsResponseBody responseBody) {
        assertEquals(responseBody.getStatusCode(), 200, "Expected status code 200 for cart creation, but found: " + responseBody.getStatusCode());
        assertNotNull(responseBody.getProductList(), "Product list should not be empty");
        assertNotNull(responseBody.getProductList().get(0).getId(), "Product id should not be null");
    }
}