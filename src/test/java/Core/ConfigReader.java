package Core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);

    public static Properties loadProperties(String env) {
        Properties prop = new Properties();

        // Default environment
        if (env == null || env.isEmpty()) env = "staging";

        // Path fleksibel: gunakan user.dir agar aman di local maupun CI/CD
        String path = System.getProperty("user.dir") + "/src/test/resources/config/" + env + ".properties";

        try (FileInputStream fis = new FileInputStream(path)) {
            prop.load(fis);
            logger.info("Loaded properties for environment: {}", env);
        } catch (IOException e) {
            logger.error("Failed to load properties file: {}", path, e);
            throw new RuntimeException("Failed to load properties", e);
        }

        return prop;
    }
}
