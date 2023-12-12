import clients.UserClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;

import static org.testng.Assert.assertEquals;

public class UserSignupTest extends BaseTest {
    private UserClient userClient;

    @BeforeClass
    public void beforeClass() {
        userClient = new UserClient();
    }

    @Test
    public void successfullySignupUser() {
        String randomEmail = RandomEmailGenerator.generateRandomEmail();
        String password = "password123";

        Response response = userClient.createUser(randomEmail, password);

        assertEquals(response.getStatusCode(), 201);
        assertEquals(response.jsonPath().getString("data.user.email"), randomEmail);
    }
}
