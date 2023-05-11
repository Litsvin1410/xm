package org.example.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertyReader {

    private static final String TEST_PROPERTIES_PATH = "src/main/resources/test.properties";

    public static String readTestProperty(TestProperty testProperty) {
        Properties properties = new Properties();
        FileInputStream input = null;

        String value = "";
        try {
            input = new FileInputStream(TEST_PROPERTIES_PATH);
            properties.load(input);

            value = properties.getProperty(testProperty.getPropertyName());
            log.info("Reading test property: {}={}", testProperty.getPropertyName(), value);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
