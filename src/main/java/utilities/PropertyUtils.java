package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    private static final Logger LOGGER = LogManager.getLogger(PropertyUtils.class);

    public static String getProperty(String propertyName) {
        String configPropertyFilePath = "src/main/resources/config.properties";

        Properties properties = new Properties();
        try {
            FileInputStream configFile = new FileInputStream(configPropertyFilePath);
            properties.load(configFile);
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            LOGGER.error("Error loading properties file", e);
            return null;
        }
    }

    public static String getProperty(String propertyName, EnvironmentUtils.Environment environment) {
        String configPropertyFilePath = EnvironmentUtils.getConfigFilePath(environment);

        Properties properties = new Properties();
        try {
            FileInputStream configFile = new FileInputStream(configPropertyFilePath);
            properties.load(configFile);
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            LOGGER.error("Error loading properties file", e);
            return null;
        }
    }
}
