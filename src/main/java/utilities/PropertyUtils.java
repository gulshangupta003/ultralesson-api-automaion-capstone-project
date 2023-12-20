package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
    private static final Logger LOGGER = LogManager.getLogger(PropertyUtils.class);
    private static final String DEFAULT_CONFIG_FILE_PATH = "src/main/resources/config.properties";

    public static String getProperty(String propertyName) {
        return getProperty(propertyName, DEFAULT_CONFIG_FILE_PATH);
    }

    public static String getProperty(String propertyName, EnvironmentUtils.Environment environment) {
        String configFilePath = EnvironmentUtils.getConfigFilePath(environment);
        return getProperty(propertyName, configFilePath);
    }

    private static String getProperty(String propertyName, String configFilePath) {
        Properties properties = new Properties();

        try (InputStream configFile = new FileInputStream(configFilePath)) {
            properties.load(configFile);

            return properties.getProperty(propertyName);
        } catch (IOException e) {
            LOGGER.error("Error while loading properties file", e);
            return null;
        }
    }
}
