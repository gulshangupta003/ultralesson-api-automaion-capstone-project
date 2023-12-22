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

    public void assertCreateCartResponseBody(CreateCartResponseBody responseBody) {
        assertEquals(responseBody.getStatusCode(), 201, "Expected status code 201 for cart creation, but found: " + responseBody.getStatusCode());
        assertNotNull(responseBody.getCartId(), "The 'cartId' should not be null upon successful cart creation.");
        assertNotNull(responseBody.getUserId(), "The 'userId' should not be null upon successful cart creation.");
        assertNotNull(responseBody.getCreatedAt(), "The 'createdAt' timestamp should not be null upon successful cart creation.");
        assertNull(responseBody.getMessage(), "There should be no message upon successful cart creation.");
    }
}