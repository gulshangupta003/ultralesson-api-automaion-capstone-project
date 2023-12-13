package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataProvider {
    /**
     * Retrieves user data from a JSON file based on the provided user key.
     *
     * @param filePath  The path to the JSON file containing user data.
     * @param userKey   The key identifying the user within the JSON file.
     * @param classType The class type representing the desired data type.
     * @return An object of type T representing the data, or null if an error occurs.
     * @throws IOException If the provided file path is null or empty.
     */
    public static <T> T getData(String filePath, String userKey, Class<T> classType) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(filePath);
            JsonNode jsonData = objectMapper.readTree(file);
            JsonNode userDataNode = jsonData.get(userKey);

            return objectMapper.treeToValue(userDataNode, classType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
