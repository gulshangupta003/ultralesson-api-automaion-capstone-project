import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utilities.PropertyUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseTest {
    @BeforeClass
    public void setup() {
        String baseUrl = PropertyUtils.getProperty("base.url");
        RestAssured.baseURI = baseUrl;
    }

    /**
     * Validates the format of an email address.
     *
     * @param email email The email address to validate.
     * @return True if the email address has a valid format, false otherwise.
     */
    public boolean isValidEmailFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
