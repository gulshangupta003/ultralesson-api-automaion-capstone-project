import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import utilities.PropertyUtil;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BaseTest {
    @BeforeClass
    public void setup() {
        String baseUrl = PropertyUtil.getProperty("base.url");
        RestAssured.baseURI = baseUrl;
    }

    public void assertSignupResponse(Response response) {
        assertEquals(response.getStatusCode(), 201);
    }

    public void assertLoginResponse(Response response) {
        assertEquals(response.getStatusCode(), 200, "Status code is not valid");
        assertNotNull(response.jsonPath().getString("data.session.access_token"), "Access Token should not be empty");
    }
}
