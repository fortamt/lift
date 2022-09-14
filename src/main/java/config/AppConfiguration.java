package config;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class AppConfiguration {

    private final Integer maxStages;
    private final Integer minStages;
    private final Integer liftCapacity;
    private final Integer maxPassengers;
    private final Integer minPassengers;

    private static AppConfiguration appConfiguration;

    private AppConfiguration() {
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            this.maxStages = Integer.valueOf(properties.getProperty("building.max_stages"));
            this.minStages = Integer.valueOf(properties.getProperty("building.min_stages"));
            this.liftCapacity = Integer.valueOf(properties.getProperty("lift.capacity"));
            this.maxPassengers = Integer.valueOf(properties.getProperty("stage.max_passengers"));
            this.minPassengers = Integer.valueOf(properties.getProperty("stage.min_passengers"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        } catch (IOException e) {
            throw new RuntimeException("Some IOException");
        }
    }

    public static AppConfiguration getAppConfiguration() {
        if (appConfiguration == null) {
            appConfiguration = new AppConfiguration();
        }
        return appConfiguration;
    }

}
