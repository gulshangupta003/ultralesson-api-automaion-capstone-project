import clients.UserClient;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;

import static org.testng.Assert.assertEquals;

public class UserSignupTest extends BaseTest {
    private UserClient userClient;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
    }

    @Test
    public void successfullySignupUser() {
        // Arrange
        String randomEmail = RandomEmailGenerator.generateRandomEmail();
        String password = "password123";

        // Act
        Response response = userClient.createUser(randomEmail, password);

        // Assert
        assertEquals(response.getStatusCode(), 201);
        assertEquals(response.jsonPath().getString("data.user.email"), randomEmail);
    }
}
