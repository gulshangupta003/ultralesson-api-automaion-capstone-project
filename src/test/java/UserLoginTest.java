import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserLoginTest extends BaseTest {
    @Test
    public void userLogin() {
        String randomEmail = RandomEmailGenerator.generateRandomEmail();
        String password = "password123";

        String requestBody = String.format("{\n" +
                "    \"email\": \"%s\",\n" +
                "    \"password\": \"%s\"\n" +
                "}", randomEmail, password);

        Response signupResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/auth/signup");

        Response signinResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/auth/login");

        assertEquals(signinResponse.getStatusCode(), 200, "Status code is not valid");
        assertEquals(signinResponse.jsonPath().getString("data.user.email"), randomEmail, "Email Id is not matching");
        assertNotNull(signinResponse.jsonPath().getString("data.session.access_token"), "Access Token should not be empty");
    }
}
