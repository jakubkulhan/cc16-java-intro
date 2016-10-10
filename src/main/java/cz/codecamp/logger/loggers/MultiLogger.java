package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.util.List;

/**
 * Created by bmandzhi
 */
public class MultiLogger implements LoggerInterface {

    private List<LoggerInterface> loggers;

    public MultiLogger(List<LoggerInterface> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for (LoggerInterface l : loggers) {
            l.log(level, message);
        }
    }
}
