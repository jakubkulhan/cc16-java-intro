package cz.codecamp.logger.util;

import java.time.LocalDateTime;

public class TimeProvider {

    private static TimeProvider instance;
    private TimeSource source = new TimeSource();

    public static synchronized TimeProvider getInstance() {
        if (instance == null) {
            instance = new TimeProvider();
        }
        return instance;
    }

    public void setSource(TimeSource source) {
        this.source = source;
    }

    public LocalDateTime getTime() {
        return source.getTime();
    }
}
