package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvironmentUtils {
    private static final Logger LOGGER = LogManager.getLogger(EnvironmentUtils.class);

    public enum Environment {
        DEV, QA, PROD
    }

    public static String getConfigFilePath(Environment environment) {
        switch (environment) {
            case DEV:
                return "src/main/resources/dev.properties";
            case QA:
                return "src/main/resources/qa.properties";
            case PROD:
                return "src/main/resources/prod.properties";
            default:
                LOGGER.error("Invalid environment: {}", environment);
                throw new IllegalArgumentException("Invalid environment");
        }
    }
}
