package utilities;

import com.github.javafaker.Faker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for generating random email addresses using the Faker library.
 */
public class RandomDataUtils {
    private Faker faker = new Faker();


    /**
     * Generates a completely random email address.
     *
     * @return A randomly generated email address.
     */
    public String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    /**
     * Generates a random email address with a random username and the specified domain.
     *
     * @param domain domain The domain for the email address.
     * @return A randomly generated email address.
     */
    public String generateRandomEmail(String domain) {
        String email = faker.name().username() + "@" + domain;

        if (!isValidEmailFormat(email)) {
            throw new IllegalArgumentException("Generated email (" + email + ") doesn't match the required format.");
        }

        return email;
    }

    /**
     * Validates the format of an email address.
     *
     * @param email email The email address to validate.
     * @return True if the email address has a valid format, false otherwise.
     */
    private boolean isValidEmailFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
