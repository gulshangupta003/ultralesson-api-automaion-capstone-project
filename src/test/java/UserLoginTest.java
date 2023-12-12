import clients.UserClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserLoginTest extends BaseTest {
    private UserClient userClient;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
    }

    @Test
    public void userShouldLoginWithValidCredentials() {
        // Arrange
        String randomEmail = RandomEmailGenerator.generateRandomEmail();
        String password = "password123";

        Response signupResponse = userClient.createUser(randomEmail, password);
        String accessToken = signupResponse.jsonPath().getString("data.session.access_token");

        // Act
        Response signinResponse = userClient.authenticateUser(randomEmail, password, accessToken);

        // Assert
        assertEquals(signinResponse.getStatusCode(), 200, "Status code is not valid");
        assertEquals(signinResponse.jsonPath().getString("data.user.email"), randomEmail, "Email Id is not matching");
        assertNotNull(signinResponse.jsonPath().getString("data.session.access_token"), "Access Token should not be empty");
    }
}
