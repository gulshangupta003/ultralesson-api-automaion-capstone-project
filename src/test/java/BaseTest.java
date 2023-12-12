import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utilities.PropertyUtil;

public class BaseTest {
    @BeforeClass
    public void setup() {
        String baseUrl = PropertyUtil.getProperty("base.url");
        RestAssured.baseURI = baseUrl;
    }
}
