import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass
    public void setup() {
        String baseUrl = PropertyUtil.getProperty("base_url");
        RestAssured.baseURI = baseUrl;
    }
}
