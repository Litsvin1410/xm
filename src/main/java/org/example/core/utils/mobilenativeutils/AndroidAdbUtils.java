package org.example.core.utils.mobilenativeutils;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

import static org.example.core.DriverFactory.getDriver;

public class AndroidAdbUtils extends MobileUtils {

    @Override
    public void pressEnter() {
        List<String> removePicsArgs = List.of("keyevent 66");
        Map<String, Object> removePicsCmd = ImmutableMap.of("command", "input", "args", removePicsArgs);
        getDriver().executeScript("mobile: shell", removePicsCmd);
    }
}
