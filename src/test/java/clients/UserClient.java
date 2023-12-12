package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserClient {
    public Response createUser(String email, String password) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(String.format("{\n" +
                        "    \"email\": \"%s\",\n" +
                        "    \"password\": \"%s\"\n" +
                        "}", email, password))
                .post("/api/auth/signup");
    }

    public Response authenticateUser(String email, String password, String accessToken) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(String.format("{\n" +
                        "    \"email\": \"%s\",\n" +
                        "    \"password\": \"%s\"\n" +
                        "}", email, password))
                .post("/api/auth/login");
    }
}
