package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.util.Arrays;

/**
 * Created by filip on 05/10/16.
 */
public class MultiLogger extends AbstractLogger {

    private LoggerInterface[] logs;

    public MultiLogger(LoggerInterface... loggers) {
        logs = loggers;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for (LoggerInterface li : logs) {
            li.log(level, message);
        }
    }

    @Override
    public void write(String msg) {
        for (LoggerInterface li : logs) {
            li.write(msg);
        }
    }
}
