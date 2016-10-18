package cz.codecamp.logger;

import java.time.LocalDateTime;

public interface LoggerInterface extends FormatterInterface {
    void log(LogLevelEnum level, String message);

    void log(LogLevelEnum level, LocalDateTime time, String message);

    void log(String message);

    default void logJson(LogLevelEnum level, String message) {
        String dateTime = LocalDateTime.now().toString();
        if (level.equals(LogLevelEnum.WARNING) || level.equals(LogLevelEnum.ERROR)) {
            log(format(level, dateTime, message));
        }
    }
}
