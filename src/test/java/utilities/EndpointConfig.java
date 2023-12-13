package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class EndpointConfig {
    private static final String CONFIG_FILE = "endpoints.json";
    private static JsonNode jsonNode;

    static {
        InputStream inputStream = null;
        try {
            inputStream = EndpointConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
            if (inputStream != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                jsonNode = objectMapper.readTree(inputStream);
            } else {
                throw new IOException("Resource not found: " + CONFIG_FILE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getEndpoint(String key, String property) {
        JsonNode endpointNode = jsonNode.path(key).path(property);
        return endpointNode.asText();
    }
}
