package Core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties loadProperties(String env) {
        Properties properties = new Properties();

        try {
            String filePath = "src/test/resources/config/" + env + ".properties";
            FileInputStream file = new FileInputStream(filePath);
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties", e);
        }

        return properties;
    }
}

