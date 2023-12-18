import clients.UserClient;
import jdk.jfr.Description;
import models.auth.LoginResponseBody;
import org.testng.annotations.Test;
import testdata.UserData;
import utilities.DataProvider;

public class UserLoginTest extends BaseTest {
    @Test
    @Description("User should be able to login successfully with valid credentials")
    public void loginSuccessfully() {
        // Arrange
        UserData validUser = new DataProvider("src/main/resources/testdata/userData.json").getData("validUser", UserData.class);
        String email = validUser.getEmail();
        String password = validUser.getPassword();

        // Act
        LoginResponseBody loginResponseBody = new UserClient().login(email, password);

        // Assert
        loginResponseBody.assertSuccessfullyLoginResponse(email);
    }
}
