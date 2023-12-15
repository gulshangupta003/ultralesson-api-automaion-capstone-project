import clients.UserClient;
import jdk.jfr.Description;
import models.auth.SignupResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.DataProvider;
import utilities.RandomDataUtils;

public class UserSignupTest extends BaseTest {
    private UserClient userClient;
    private RandomDataUtils randomDataUtils;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        randomDataUtils = new RandomDataUtils();
    }

    @Test
    @Description("User should be able to signup successfully with valid credentials")
    public void successfullySignupUser() {
        // Arrange
        String randomEmail = randomDataUtils.generateRandomEmail();
        assert isValidEmailFormat(randomEmail) : "Invalid email format: " + randomEmail;
        String password = randomDataUtils.generateRandomPassword();

        // Act
        SignupResponseBody signupResponseBodyBody = userClient.signup(randomEmail, password);

        // Assert
        signupResponseBodyBody.assertSuccessfullySignupResponse(randomEmail);
    }
}
