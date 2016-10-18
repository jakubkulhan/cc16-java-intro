package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by filip on 05/10/16.
 */
public abstract class AbstractLogger implements PragmaticLoggerInterface {

    abstract public void log(LogLevelEnum level, String message);

    abstract public void write(String msg);

    @Override
    public void debug(String message) {
        log(LogLevelEnum.DEBUG, message);
    }

    @Override
    public void info(String message) {
        log(LogLevelEnum.INFO, message);
    }

    @Override
    public void warning(String message) {
        log(LogLevelEnum.WARNING, message);
    }

    @Override
    public void error(String message) {
        log(LogLevelEnum.ERROR, message);
    }

    protected String formatMessage(String msg) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return "[" + LocalDateTime.now().format(formatter) + "] " + msg;
    }
}
