import clients.UserClient;
import models.auth.LoginResponseBody;
import models.auth.SignupResponseBody;
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

        SignupResponseBody signupResponseBody = userClient.createUser(randomEmail, password);
        String accessToken = signupResponseBody.getData().getSession().getAccessToken();

        // Act
//        LoginResponseBody loginResponseBody = userClient.authenticateUser(randomEmail, password, accessToken);
        LoginResponseBody loginResponseBody = userClient.authenticateUser(randomEmail, password, accessToken);

        // Assert
        assertEquals(loginResponseBody.getStatusCode(), 200, "Status code is not valid");
        assertEquals(loginResponseBody.getData().getUser().getEmail(), randomEmail);
        assertNotNull(loginResponseBody.getData().getSession().getAccessToken());
    }
}
