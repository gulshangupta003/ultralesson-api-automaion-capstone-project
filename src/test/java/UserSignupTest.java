import clients.UserClient;
import models.auth.SignupResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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
        SignupResponseBody signupResponseBodyBody = userClient.createUser(randomEmail, password);

        // Assert
        assertEquals(signupResponseBodyBody.getStatusCode(), 201, "Status code is not valid");
        assertEquals(signupResponseBodyBody.getData().getUser().getEmail(), randomEmail);
        assertNotNull(signupResponseBodyBody.getData().getSession().getAccessToken());
    }
}
