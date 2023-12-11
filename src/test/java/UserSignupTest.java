import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;

import static org.testng.Assert.assertEquals;

public class UserSignupTest extends BaseTest {
    @Test
    public void successfullySignupUser() {
        String randomEmail = RandomEmailGenerator.generateRandomEmail();
        String password = "password123";

        String requestBody = String.format("{\n" +
                "    \"email\": \"%s\",\n" +
                "    \"password\": \"%s\"\n" +
                "}", randomEmail, password);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/auth/signup");

        assertEquals(response.getStatusCode(), 201);
        assertEquals(response.jsonPath().getString("data.user.email"), randomEmail);
    }
}
