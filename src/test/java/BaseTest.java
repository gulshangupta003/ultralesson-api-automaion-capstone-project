import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utilities.PropertyUtil;

public class BaseTest {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = PropertyUtil.getProperty("base.url");
    }
}
