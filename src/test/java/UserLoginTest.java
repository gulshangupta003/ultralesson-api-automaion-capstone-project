import clients.UserClient;
import models.auth.LoginResponseBody;
import models.auth.SignupResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testdata.UserData;
import utilities.DataProvider;
import utilities.RandomDataUtils;

public class UserLoginTest extends BaseTest {
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
    public void loginSuccessfully() {
        // Arrange
        String randomEmail = randomDataUtils.generateRandomEmail("gmail.com");
        System.out.println(randomEmail);
        String password = userDataProvider.getData("validUser", UserData.class).getPassword();

        SignupResponseBody signupResponseBody = userClient.signup(randomEmail, password);
        String accessToken = signupResponseBody.getData().getSession().getAccessToken();

        // Act
        LoginResponseBody loginResponseBody = userClient.login(randomEmail, password, accessToken);

        // Assert
        loginResponseBody.assertSuccessfullyLoginResponse(randomEmail);
    }
}
