package org.example.core.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@Getter
public enum WaitDuration {

    SHORTEST(Duration.of(1, ChronoUnit.SECONDS)),
    SHORT(Duration.of(5, ChronoUnit.SECONDS)),
    MEDIUM(Duration.of(15, ChronoUnit.SECONDS)),
    LONG(Duration.of(30, ChronoUnit.SECONDS));

    private Duration duration;
}
