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
        assertEquals(createCartResponseBody.getStatusCode(), 201, "Invalid status code");
        assertNotNull(createCartResponseBody.getCartId());
        assertNotNull(createCartResponseBody.getUserId());
        assertNotNull(createCartResponseBody.getCreatedAt());
        assertNull(createCartResponseBody.getMessage());
    }
}