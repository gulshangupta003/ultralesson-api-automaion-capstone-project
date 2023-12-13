package utilities;

import com.github.javafaker.Faker;

public class RandomEmailGenerator {
    public static String generateRandomEmail() {
        Faker faker = new Faker();

        return faker.internet().emailAddress();
    }
}
