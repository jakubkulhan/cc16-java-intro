package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.util.List;

/**
 * Created by Martin on 07.10.2016.
 */
public class MultiLogger implements LoggerInterface {
    private List<LoggerInterface> loggers;

    public MultiLogger(List<LoggerInterface> loggers){
        this.loggers = loggers;
    }

    public void log(LogLevelEnum level, String message, int minLogLevel) {
        for (LoggerInterface logger : loggers) {
            logger.log(level, message, minLogLevel);
        }
    }
}

