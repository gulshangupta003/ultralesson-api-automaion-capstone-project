package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.auth.LoginRequestBody;
import models.auth.LoginResponseBody;
import models.auth.SignupRequestBody;
import models.auth.SignupResponseBody;

public class UserClient {
    public SignupResponseBody createUser(String email, String password) {
        SignupRequestBody signupRequestBodyBody = SignupRequestBody.builder()
                .email(email)
                .password(password)
                .build();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(signupRequestBodyBody)
                .post("/api/auth/signup");

        SignupResponseBody signupResponseBodyBody = response.as(SignupResponseBody.class);
        signupResponseBodyBody.setStatusCode(response.getStatusCode());

        return signupResponseBodyBody;
    }

    public LoginResponseBody authenticateUser(String email, String password, String accessToken) {
        LoginRequestBody loginRequestBody = LoginRequestBody.builder()
                .email(email)
                .password(password)
                .build();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(String.format("{\n" +
                        "    \"email\": \"%s\",\n" +
                        "    \"password\": \"%s\"\n" +
                        "}", email, password))
                .post("/api/auth/login");

        LoginResponseBody loginResponseBody = response.as(LoginResponseBody.class);
        loginResponseBody.setStatusCode(response.getStatusCode());

        return loginResponseBody;
    }
}
