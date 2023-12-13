package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import testdata.UserData;

import java.io.File;
import java.io.IOException;

public class JsonDataReaderUtils {
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
