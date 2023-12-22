package models.cartItems;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Getter
@Setter
public class AddItemToCartResponseBody {
    private int statusCode;
    @JsonProperty("cart_id")
    private String cartId;
    private int quantity;
    private Object price;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("cart_item_id")
    private String cartItemId;

    public void assertAddItemToCartResponseBody(AddItemToCartResponseBody responseBody, String cartId, String productId, int quantity) {
        assertEquals(responseBody.getStatusCode(), 201,
                "Expected status code 201, but found: " + responseBody.getStatusCode());
        assertEquals(responseBody.getCartId(), cartId, "Cart id is not matching");
        assertEquals(responseBody.getProductId(), productId, "Product id is is not matching");
        assertEquals(responseBody.getQuantity(), quantity,
                "Quantity is not matching");
        assertNotNull(responseBody.cartItemId, "Cart item id should not be null");
        assertNotNull(responseBody.getPrice(), "Price should not be null");
    }
}
