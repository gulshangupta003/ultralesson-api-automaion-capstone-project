import clients.UserClient;
import jdk.jfr.Description;
import models.auth.SignupResponseBody;
import org.testng.annotations.Test;
import utilities.RandomDataUtils;

public class UserSignupTest extends BaseTest {
    @Test
    @Description("User should be able to signup successfully with valid credentials")
    public void successfullySignupUser() {
        // Arrange
        RandomDataUtils randomDataUtils = new RandomDataUtils();
        String randomEmail = randomDataUtils.generateRandomEmail();
        assert isValidEmailFormat(randomEmail) : "Invalid email format: " + randomEmail;
        String password = randomDataUtils.generateRandomPassword();

        // Act
        SignupResponseBody signupResponseBodyBody = new UserClient().signup(randomEmail, password);

        // Assert
        signupResponseBodyBody.assertSuccessfullySignupResponse(randomEmail);
    }
}
