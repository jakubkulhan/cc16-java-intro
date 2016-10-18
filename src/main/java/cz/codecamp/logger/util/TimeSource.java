package cz.codecamp.logger.util;

import java.time.LocalDateTime;

public class TimeSource {

    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
