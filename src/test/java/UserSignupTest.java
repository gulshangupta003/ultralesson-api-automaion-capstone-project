import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSignupTest extends BaseTest {
    @Test
    public void successfullySignupUser() {
        String requestBody = "{\n" +
                "    \"email\": \"user1234567@example.com\",\n" +
                "    \"password\": \"password123\"\n" +
                "}";

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/auth/signup");

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("data.user.email"), "user1234567@example.com");
    }
}
