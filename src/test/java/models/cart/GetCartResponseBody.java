package models.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Getter
@Setter
public class GetCartResponseBody {
    private int statusCode;
    @JsonProperty("cart_id")
    private String cartId;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("created_at")
    private String createdAt;

    public void assertGetCartResponseBody(GetCartResponseBody getCartResponseBody) {
        assertEquals(getCartResponseBody.getStatusCode(), 200, "Invalid status code");
        assertNotNull(getCartResponseBody.getCartId());
        assertNotNull(getCartResponseBody.getUserId());
        assertNotNull(getCartResponseBody.getCreatedAt());
    }
}