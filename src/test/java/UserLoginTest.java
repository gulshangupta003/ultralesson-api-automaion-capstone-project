import clients.UserClient;
import io.qameta.allure.Description;
import models.auth.LoginResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import testdata.UserData;
import utilities.DataProvider;

public class UserLoginTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(UserLoginTest.class);

    @Test
    @Description("User should be able to login successfully with valid credentials")
    public void loginSuccessfully() {
        // Arrange
        LOGGER.info("Starting loginSuccessfully test...");
        UserData validUser = new DataProvider("src/main/resources/testdata/userData.json").getData("validUser", UserData.class);
        String email = validUser.getEmail();
        String password = validUser.getPassword();

        // Act
        LoginResponseBody loginResponseBody = new UserClient().login(email, password);

        // Assert
        loginResponseBody.assertSuccessfullyLoginResponse(email);
        LOGGER.info("loginSuccessfully test completed successfully.");
    }
}
