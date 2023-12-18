package utilities;

import com.github.javafaker.Faker;

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
        return faker.name().username() + "@" + domain;
    }

    /**
     * Generates a random password using the Faker library.
     *
     * @return A randomly generated password.
     */
    public String generateRandomPassword() {
        return faker.internet().password(8, 16, true, true, true);
    }
}
