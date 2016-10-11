package cz.codecamp.logger.formatters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by micha on 05.10.2016.
 */
public class SimpleFormatter implements FormatterInterface {

    private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    @Override
    public String format( LogLevelEnum level, String message ) {
        String time = LocalDateTime.now().format( DateTimeFormatter.ofPattern( FORMAT_DATE_TIME ) );
        return String.format( "[%s] [%s] %s", level.name().toUpperCase(), time, message );
    }

    @Override
    public String format( LogLevelEnum level, String message, String callingClass, int callingLineNumber ) {
        String time = LocalDateTime.now().format( DateTimeFormatter.ofPattern( FORMAT_DATE_TIME ) );
        return String.format( "[%s] [%s] [%s] %s", level.name().toUpperCase(), time, callingClass + ":" + callingLineNumber, message );
    }
}
