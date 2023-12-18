package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.exceptions.DataProviderException;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataProvider {
    private String filePath;
    private final Map<String, JsonNode> cache = new ConcurrentHashMap<>();

    public DataProvider(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves data of type T from a JSON file based on the provided key.
     *
     * @param <T>       The type of the data to retrieve.
     * @param dataKey   The key identifying the user within the JSON file.
     * @param classType The class type representing the desired data type.
     * @return An object of type T representing the data, or null if an error occurs.
     * @throws IOException If the provided file path is null or empty.
     */
    public synchronized <T> T getData(String dataKey, Class<T> classType) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                throw new DataProviderException("File not found: " + filePath);
            }

            JsonNode jsonData = cache.computeIfAbsent(dataKey, k -> {
                try {
                    return objectMapper.readTree(file).get(k);
                } catch (IOException e) {
                    throw new DataProviderException("An I/O error occurred when accessing file: " + filePath, e);
                }
            });

            if (jsonData == null) {
                throw new DataProviderException("Data key not found: " + dataKey);
            }

            return objectMapper.treeToValue(jsonData, classType);
        } catch (IOException e) {
            throw new DataProviderException("An I/O error occurred when accessing file: " + filePath, e);
        }
    }
}
