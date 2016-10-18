package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StdoutLogger extends AbstractLogger {

    @Override
    public void log(LogLevelEnum level, String message) {
        System.out.printf("[%s]: %s\n", level.name(), formatMessage(message));
    }

    @Override
    public void write(String msg) {
        System.out.println(msg);
    }

}
