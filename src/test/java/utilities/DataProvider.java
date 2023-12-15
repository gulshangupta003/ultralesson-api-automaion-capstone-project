package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.exceptions.DataProviderException;

import java.io.File;
import java.io.IOException;

public class DataProvider {
    private String filePath;

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
    public <T> T getData(String dataKey, Class<T> classType) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                throw new DataProviderException("File not found: " + filePath);
            }

            JsonNode jsonData = objectMapper.readTree(file);
            JsonNode userDataNode = jsonData.get(dataKey);

            if (userDataNode == null) {
                throw new DataProviderException("Data key not found: " + dataKey);
            }

            return objectMapper.treeToValue(userDataNode, classType);
        } catch (IOException e) {
            throw new DataProviderException("An I/O error occurred when accessing file: " + filePath, e);
        }
    }
}
