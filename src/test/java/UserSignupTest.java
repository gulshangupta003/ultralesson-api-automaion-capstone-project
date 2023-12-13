import clients.UserClient;
import models.auth.SignupResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testdata.UserData;
import utilities.DataProvider;
import utilities.RandomEmailGenerator;

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
        String password = DataProvider.getData("src/main/resources/testdata/userData.json", "validUser", UserData.class).getPassword();

        // Act
        SignupResponseBody signupResponseBodyBody = userClient.signup(randomEmail, password);

        // Assert
        signupResponseBodyBody.assertSuccessfullySignupResponse(randomEmail);
    }
}
