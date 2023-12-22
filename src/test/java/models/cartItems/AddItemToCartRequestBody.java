package models.cartItems;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddItemToCartRequestBody {
    private int quantity;
    @JsonProperty("product_id")
    private String productId;
}
