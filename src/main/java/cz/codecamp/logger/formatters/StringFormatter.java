package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogLevelEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by vkorecky on 7.10.16.
 */
public class StringFormatter extends AbstractFormatter {
    @Override
    public String format(LogLevelEnum level, String message) {
        return String.format("[%s] [%s]: %s", level.name(), timeFormat.format(System.currentTimeMillis()), message);
    }
}
