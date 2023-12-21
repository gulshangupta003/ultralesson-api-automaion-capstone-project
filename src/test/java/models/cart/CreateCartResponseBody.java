package models.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import static org.testng.Assert.*;

@Getter
@Setter
public class CreateCartResponseBody {
    private int statusCode;
    @JsonProperty("cart_id")
    private String cartId;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("created_at")
    private String createdAt;
    private String message;

    public void assertCreateCartResponseBody(CreateCartResponseBody createCartResponseBody) {
        assertEquals(createCartResponseBody.getStatusCode(), 201, "Expected status code 201 for cart creation, but found: " + createCartResponseBody.getStatusCode());
        assertNotNull(createCartResponseBody.getCartId(), "The 'cartId' should not be null upon successful cart creation.");
        assertNotNull(createCartResponseBody.getUserId(), "The 'userId' should not be null upon successful cart creation.");
        assertNotNull(createCartResponseBody.getCreatedAt(), "The 'createdAt' timestamp should not be null upon successful cart creation.");
        assertNull(createCartResponseBody.getMessage(), "There should be no message upon successful cart creation.");
    }
}