import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utilities.PropertyUtils;

public class BaseTest {
    @BeforeClass
    public void setup() {
        String baseUrl = PropertyUtils.getProperty("base.url");
        RestAssured.baseURI = baseUrl;
    }
}
