package models.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupResponseBody {
    private int statusCode;
    private Data data;

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private Session session;
        private User user;
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private String email;
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Session {
        @JsonProperty("access_token")
        private String accessToken;
    }
}