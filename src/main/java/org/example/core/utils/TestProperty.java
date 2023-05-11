package org.example.core.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestProperty {

    DEVICE_UDID("device.udid"),
    APP_PATH("app"),
    APP_ACTIVITY("app.activity"),
    APP_WAIT_ACTIVITY("app.wait.activity"),
    NEW_COMMAND_TIMEOUT("new.command.timeout.seconds"),
    URL("url"),
    IMAGE_MATCH_THRESHOLD("image.match.threshold");

    private String propertyName;
}
