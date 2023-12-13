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
        UserData validUser = userDataProvider.getData("validUser", UserData.class);
        String email = validUser.getEmail();
        String password = validUser.getPassword();

        // Act
        LoginResponseBody loginResponseBody = userClient.login(email, password);

        // Assert
        loginResponseBody.assertSuccessfullyLoginResponse(email);
    }
}
