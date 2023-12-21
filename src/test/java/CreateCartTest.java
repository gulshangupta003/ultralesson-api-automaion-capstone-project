import clients.CartClient;
import clients.UserClient;
import jdk.jfr.Description;
import models.cart.CreateCartResponseBody;
import models.cart.DeleteCartResponseBody;
import models.cart.GetCartResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomDataUtils;

import static org.testng.Assert.assertEquals;

public class CreateCartTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(CreateCartTest.class);
    private UserClient userClient;
    private RandomDataUtils randomDataUtils;
    private CartClient cartClient;

    @BeforeClass
    public void createCartBeforeClass() {
        userClient = new UserClient();
        randomDataUtils = new RandomDataUtils();
        cartClient = new CartClient();
    }

    @Test
    @Description("User should be able to create cart successfully with valid credentials.")
    public void shouldAbleToCreateCart() {

        String randomEmail = randomDataUtils.generateRandomEmail();
        String password = randomDataUtils.generateRandomPassword();

        String accessToken = userClient.signup(randomEmail, password)
                .getData()
                .getSession()
                .getAccessToken();

        CreateCartResponseBody createCartResponseBody = cartClient.createCart(accessToken);
        createCartResponseBody.assertCreateCartResponseBody(createCartResponseBody);

        GetCartResponseBody getCartResponseBody = cartClient.getCart(accessToken);
        getCartResponseBody.assertGetCartResponseBody(getCartResponseBody);
        String cartId = createCartResponseBody.getCartId();

        assertEquals(cartId, getCartResponseBody.getCartId(), "Cart ID is not matching");

        DeleteCartResponseBody deleteCartResponseBody = cartClient.deleteCart(accessToken, cartId);
        Assert.assertEquals(deleteCartResponseBody.getStatusCode(), 204, "Invalid status code");

        LOGGER.info("shouldAbleToCreateCart test completed successfully.");
    }
}
