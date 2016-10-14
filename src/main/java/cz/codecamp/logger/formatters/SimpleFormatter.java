package cz.codecamp.logger.formatters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.utils.LocalDateTimeUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by micha on 05.10.2016.
 */
public class SimpleFormatter implements FormatterInterface {

    private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";


    @Override
    public String format( LogLevelEnum level, String message, long timeInMillis, String callingClass, int callingLineNumber ) {
        String time = LocalDateTimeUtils.fromMillis( timeInMillis ).format( DateTimeFormatter.ofPattern( FORMAT_DATE_TIME ) );
        return String.format( "[%s] [%s] [%s] %s", level.name().toUpperCase(), time, callingClass + ":" + callingLineNumber, message );
    }
}
