package utilities;

import com.github.javafaker.Faker;

import java.util.UUID;

public class RandomEmailGenerator {
    public static String generateRandomEmail() {
        Faker faker = new Faker();

        return faker.internet().emailAddress();
    }
}
