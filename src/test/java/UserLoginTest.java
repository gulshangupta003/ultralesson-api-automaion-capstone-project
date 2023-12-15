import clients.UserClient;
import jdk.jfr.Description;
import models.auth.LoginResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testdata.UserData;
import utilities.DataProvider;

public class UserLoginTest extends BaseTest {
    private UserClient userClient;
    private DataProvider userDataProvider;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
        userDataProvider = new DataProvider("src/main/resources/testdata/userData.json");
    }

    @Test
    @Description("User should be able to login successfully with valid credentials")
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
