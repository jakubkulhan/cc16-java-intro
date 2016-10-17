package cz.codecamp.logger.formatters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Created by lenka.salacova on 10/15/2016.
 */
public class DateFormatter implements FormatterInterface {

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public String format(LogLevelEnum level, String message) {
        return "[" + level + "] [" + LocalDateTime.now().format(dateFormat) + "] " + message;

    }
}



