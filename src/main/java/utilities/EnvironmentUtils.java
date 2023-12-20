package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class EnvironmentUtils {
    private static final Logger LOGGER = LogManager.getLogger(EnvironmentUtils.class);

    public enum Environment {
        DEV, QA, PROD
    }

    public static String getConfigFilePath(Environment environment) {
        switch (environment) {
            case DEV:
                return "src" + File.separator + "main" + File.separator + "resources" + File.separator + "dev.properties";
            case QA:
                return "src" + File.separator + "main" + File.separator + "resources" + File.separator + "qa.properties";
            case PROD:
                return "src" + File.separator + "main" + File.separator + "resources" + File.separator + "prod.properties";
            default:
                LOGGER.error("Invalid environment: {}", environment);
                throw new IllegalArgumentException("Invalid environment: " + environment);
        }
    }

    public static Environment getTestEnvironment() {
        String env = System.getProperty("env", "QA");
        return EnvironmentUtils.Environment.valueOf(env);
    }
}
