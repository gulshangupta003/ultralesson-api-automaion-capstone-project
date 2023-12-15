import clients.UserClient;
import models.auth.SignupResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testdata.UserData;
import utilities.DataProvider;
import utilities.RandomDataUtils;

public class UserSignupTest extends BaseTest {
    private UserClient userClient;
    private DataProvider userDataProvider;
    private RandomDataUtils randomDataUtils;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userDataProvider = new DataProvider("src/main/resources/testdata/userData.json");
        randomDataUtils = new RandomDataUtils();
    }

    @Test
    public void successfullySignupUser() {
        // Arrange
        String randomEmail = randomDataUtils.generateRandomEmail();
        String password = userDataProvider.getData("validUser", UserData.class).getPassword();

        // Act
        SignupResponseBody signupResponseBodyBody = userClient.signup(randomEmail, password);

        // Assert
        signupResponseBodyBody.assertSuccessfullySignupResponse(randomEmail);
    }
}
