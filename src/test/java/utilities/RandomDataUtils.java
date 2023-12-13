package utilities;

import com.github.javafaker.Faker;

public class RandomDataUtils {
    private Faker faker = new Faker();

    public String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public String generateRandomEmail(String domain) {
        return faker.name().username() + "@" + domain;
    }
}
