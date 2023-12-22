import clients.UserClient;
import io.qameta.allure.Description;
import models.auth.SignupResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utilities.RandomDataUtils;

public class UserSignupTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(UserSignupTest.class);

    @Test
    @Description("User should be able to signup successfully with valid credentials")
    public void successfullySignupUser() {
        // Arrange
        LOGGER.info("Starting successfullySignupUser test...");
        RandomDataUtils randomDataUtils = new RandomDataUtils();
        String randomEmail = randomDataUtils.generateRandomEmail();
        assert isValidEmailFormat(randomEmail) : "Invalid email format: " + randomEmail;
        String password = randomDataUtils.generateRandomPassword();

        // Act
        SignupResponseBody signupResponseBodyBody = new UserClient().signup(randomEmail, password);

        // Assert
        signupResponseBodyBody.assertSuccessfullySignupResponse(randomEmail);
        LOGGER.info("successfullySignupUser test completed successfully.");
    }
}
