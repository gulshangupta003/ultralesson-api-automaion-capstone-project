package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import testdata.UserData;

import java.io.File;
import java.io.IOException;

public class JsonDataReaderUtils {
    /**
     * Retrieves user data from a JSON file based on the provided user key.
     *
     * @param filePath The path to the JSON file containing user data.
     * @param userKey  The key identifying the user within the JSON file.
     * @return A UserData object representing the user data, or null if an error occurs.
     * @throws IOException If the provided file path is null or empty.
     */
    public static UserData getUserData(String filePath, String userKey) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(filePath);
            JsonNode jsonData = objectMapper.readTree(file);
            JsonNode userDataNode = jsonData.get(userKey);

            return objectMapper.treeToValue(userDataNode, UserData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
