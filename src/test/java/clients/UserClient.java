package clients;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listener.RestAssuredListener;
import models.auth.LoginRequestBody;
import models.auth.LoginResponseBody;
import models.auth.SignupRequestBody;
import models.auth.SignupResponseBody;
import utilities.EndpointConfig;

import static io.restassured.RestAssured.given;

public class UserClient {
    public SignupResponseBody signup(String email, String password) {
        String signupEndpoint = EndpointConfig.getEndpoint("auth", "signup");

        SignupRequestBody signupRequestBodyBody = SignupRequestBody.builder()
                .email(email)
                .password(password)
                .build();

        Response response = given()
                .filter(new RestAssuredListener())
                .contentType(ContentType.JSON)
                .body(signupRequestBodyBody)
                .when()
                .post(signupEndpoint);

        SignupResponseBody signupResponseBodyBody = response.as(SignupResponseBody.class);
        signupResponseBodyBody.setStatusCode(response.getStatusCode());

        return signupResponseBodyBody;
    }

    public LoginResponseBody login(String email, String password) {
        String loginEndpoint = EndpointConfig.getEndpoint("auth", "login");

        LoginRequestBody loginRequestBody = LoginRequestBody.builder()
                .email(email)
                .password(password)
                .build();

        Response response = given()
                .filter(new RestAssuredListener())
                .contentType(ContentType.JSON)
                .body(loginRequestBody)
                .when()
                .post(loginEndpoint);

        LoginResponseBody loginResponseBody = response.as(LoginResponseBody.class);
        loginResponseBody.setStatusCode(response.getStatusCode());

        return loginResponseBody;
    }
}
