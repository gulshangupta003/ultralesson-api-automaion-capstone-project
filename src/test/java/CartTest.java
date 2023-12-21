import clients.CartClient;
import clients.UserClient;
import jdk.jfr.Description;
import models.cart.CreateCartResponseBody;
import models.cart.DeleteCartResponseBody;
import models.cart.GetCartResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomDataUtils;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(CartTest.class);
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
    public void shouldBeAbleToCreateCartSuccessfully() {
        // Arrange
        LOGGER.info("createAndDeleteCart test started...");
        String randomEmail = randomDataUtils.generateRandomEmail();
        String password = randomDataUtils.generateRandomPassword();

        String accessToken = userClient.signup(randomEmail, password)
                .getData()
                .getSession()
                .getAccessToken();

        // Act
        CreateCartResponseBody createCartResponseBody = cartClient.createCart(accessToken);

        // Assert
        createCartResponseBody.assertCreateCartResponseBody(createCartResponseBody);
        GetCartResponseBody getCartResponseBody = cartClient.getCart(accessToken);
        getCartResponseBody.assertGetCartResponseBody(getCartResponseBody);
        String cartId = createCartResponseBody.getCartId();
        assertEquals(cartId, getCartResponseBody.getCartId(), "The cart IDs do not match.");

        this.deleteCart(accessToken, createCartResponseBody.getCartId());
        LOGGER.info("shouldAbleToCreateCart test completed successfully...");
    }

    @Test
    @Description("User should be able to delete the cart successfully.")
    public void shouldDeleteCartSuccessfully() {
        // Arrange
        LOGGER.info("shouldDeleteCartSuccessfully test started...");
        String randomEmail = randomDataUtils.generateRandomEmail();
        String password = randomDataUtils.generateRandomPassword();

        String accessToken = userClient.signup(randomEmail, password)
                .getData()
                .getSession()
                .getAccessToken();

        CreateCartResponseBody createCartResponseBody = cartClient.createCart(accessToken);
        createCartResponseBody.assertCreateCartResponseBody(createCartResponseBody);

        GetCartResponseBody getCartResponseBody1 = cartClient.getCart(accessToken);
        getCartResponseBody1.assertGetCartResponseBody(getCartResponseBody1);

        // Act
        this.deleteCart(accessToken, createCartResponseBody.getCartId());

        // Assert
        GetCartResponseBody getCartResponseBody2 = cartClient.getCart(accessToken);
        assertEquals(getCartResponseBody2.getMessage(), "No cart found",
                "After deleting cart cart should not available");
        LOGGER.info("shouldDeleteCartSuccessfully test completed successfully...");
    }

    @Test
    @Description("Create cart when the cart is already created.")
    public void shouldFailToCreateDuplicateCart() {
        // Arrange
        LOGGER.info("shouldNotBeAbleToCreateCartWhenCartAlreadyExists test started...");
        String randomEmail = randomDataUtils.generateRandomEmail();
        String password = randomDataUtils.generateRandomPassword();

        String accessToken = userClient.signup(randomEmail, password)
                .getData()
                .getSession()
                .getAccessToken();

        CreateCartResponseBody createCartResponseBody1 = cartClient.createCart(accessToken);
        createCartResponseBody1.assertCreateCartResponseBody(createCartResponseBody1);

        // Act
        CreateCartResponseBody createCartResponseBody2 = cartClient.createCart(accessToken);

        // Assert
        assertEquals(createCartResponseBody2.getMessage(), "Cart already created for the user",
                "Response message is not matching when the cart is already created");

        this.deleteCart(accessToken, createCartResponseBody1.getCartId());
        LOGGER.info("shouldNotBeAbleToCreateCartWhenCartAlreadyExists test completed successfully...");
    }

    @Test
    @Description("User should not be able to delete cart with invalid cart ID")
    public void shouldFailToDeleteCartWithInvalidCartId() {
        // Arrange
        LOGGER.info("shouldFailToDeleteCartWithInvalidCartId test started...");
        String randomEmail = randomDataUtils.generateRandomEmail();
        String password = randomDataUtils.generateRandomPassword();

        String accessToken = userClient.signup(randomEmail, password)
                .getData()
                .getSession()
                .getAccessToken();

        CreateCartResponseBody createCartResponseBody = cartClient.createCart(accessToken);

        GetCartResponseBody getCartResponseBody = cartClient.getCart(accessToken);
        getCartResponseBody.assertGetCartResponseBody(getCartResponseBody);
        assertEquals(getCartResponseBody.getCartId(), createCartResponseBody.getCartId(), "Cart Id is not matching");

        // Act
        DeleteCartResponseBody deleteCartResponseBody = cartClient.deleteCart(accessToken,
                createCartResponseBody.getCartId() + "invalid");

        // Assert
        assertEquals(deleteCartResponseBody.getStatusCode(), 500, "Expected status code 500, but found: " + deleteCartResponseBody.getStatusCode());
        assertEquals(deleteCartResponseBody.getError(), String.format("invalid input syntax for type uuid: \"%sinvalid\"",
                createCartResponseBody.getCartId()));

        LOGGER.info("shouldFailToDeleteCartWithInvalidCartId test completed successfully...");
    }

    @Test
    @Description("Attempting to create a cart with an invalid (unauthorized) token should throw ApiException with status code 401.")
    public void shouldNotCreateCartWhenUnauthorized() {
        // Arrange
        LOGGER.info("shouldNotCreateCartWhenUnauthorized test started...");
        String invalidToken = "invalid-token";

        // Act
        CreateCartResponseBody createCartResponseBody = cartClient.createCart(invalidToken);

        // Assert
        assertEquals(createCartResponseBody.getStatusCode(), 401, "Excepted 401 for invalid token");
        assertEquals(createCartResponseBody.getMessage(), "Token is invalid or expired.");
        LOGGER.info("shouldNotCreateCartWhenUnauthorized test completed successfully...");
    }

    public void deleteCart(String accessToken, String cartId) {
        DeleteCartResponseBody deleteCartResponseBody = cartClient.deleteCart(accessToken, cartId);
        assertEquals(deleteCartResponseBody.getStatusCode(), 204, "Invalid status code");
    }
}
