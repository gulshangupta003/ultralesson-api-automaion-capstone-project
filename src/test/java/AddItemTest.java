import clients.CartClient;
import clients.CartItemClients;
import clients.ProductClient;
import clients.UserClient;
import io.qameta.allure.Description;
import models.cart.CreateCartResponseBody;
import models.cartItems.AddItemToCartResponseBody;
import models.product.GetProductsResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomDataUtils;

public class AddItemTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(CartTest.class);
    private UserClient userClient;
    private RandomDataUtils randomDataUtils;
    private CartClient cartClient;
    private ProductClient productClient;
    private CartItemClients cartItemClients;

    @BeforeClass
    public void addItemBeforeClass() {
        userClient = new UserClient();
        randomDataUtils = new RandomDataUtils();
        cartClient = new CartClient();
        productClient = new ProductClient();
        cartItemClients = new CartItemClients();
    }

    @Test
    @Description("User should be able to add item to the cart")
    public void shouldAddItemToCart() {
        // Arrange
        LOGGER.info("shouldAddItemToCart test started...");
        String randomEmail = randomDataUtils.generateRandomEmail();
        String password = randomDataUtils.generateRandomPassword();

        String accessToken = userClient.signup(randomEmail, password)
                .getData()
                .getSession()
                .getAccessToken();

        CreateCartResponseBody createCartResponseBody = cartClient.createCart(accessToken);

        GetProductsResponseBody getProductsResponseBody = productClient.getProducts(accessToken);
        getProductsResponseBody.assertGetProductsResponseBody(getProductsResponseBody);

        String cartId = createCartResponseBody.getCartId();
        GetProductsResponseBody.Product productDetails = getProductsResponseBody.getProductDetails("Smartphone");
        String productId = productDetails.getId();
        Assert.assertNotNull(productDetails, "Product not found");
        int quantity = 10;

        // Act
        AddItemToCartResponseBody addItemToCartResponseBody = cartItemClients.addItemToCart(accessToken, cartId, productId, quantity);

        // Assert
        addItemToCartResponseBody.assertAddItemToCartResponseBody(addItemToCartResponseBody, cartId, productId, quantity);
        LOGGER.info("shouldAddItemToCart test completed successfully...");
    }
}
