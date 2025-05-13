package com.jithendra.tracker.studentperformance.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * @author Narala Jithendra
 */
public class ConfigLoader {

    private static final Properties properties = new Properties();
    private static final Logger log = LogManager.getLogger(ConfigLoader.class);

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                log.error("Could not find config.properties file"+ " user.dir: "+ System.getProperty("user.dir"));
                throw new RuntimeException("Unable to find config.properties");
            }
            log.info("Loading config.properties from user.dir: "+ " user.dir: "+System.getProperty("user.dir"));
            properties.load(input);
        } catch (IOException e) {
            log.error("Unable to load config.properties", e);
            throw new RuntimeException("Error loading configuration: " + e.getMessage(), e);
        }
    }

    public static String get(String key) {
        log.info("Loading configuration: " + key+ "= "+ properties.getProperty(key));
        return properties.getProperty(key);
    }
}
