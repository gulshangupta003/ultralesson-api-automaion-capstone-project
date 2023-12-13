import clients.UserClient;
import models.auth.LoginResponseBody;
import models.auth.SignupResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testdata.UserData;
import utilities.DataProvider;
import utilities.RandomEmailGenerator;

public class UserLoginTest extends BaseTest {
    private UserClient userClient;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
    }

    @Test
    public void loginSuccessfully() {
        // Arrange
        String randomEmail = RandomEmailGenerator.generateRandomEmail();
        String password = DataProvider.getData("src/main/resources/testdata/userData.json", "validUser", UserData.class).getPassword();

        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);
        String accessToken = signupResponseBody.getData().getSession().getAccessToken();

        // Act
        LoginResponseBody loginResponseBody = userClient.login(randomEmail, password, accessToken);

        // Assert
        loginResponseBody.assertSuccessfullyLoginResponse(randomEmail);
    }
}
