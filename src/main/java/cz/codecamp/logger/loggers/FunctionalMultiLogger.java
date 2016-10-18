package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.util.List;

public class FunctionalMultiLogger implements LoggerInterface {

    List<LoggerInterface> listOfLoggers;

    public FunctionalMultiLogger(List<LoggerInterface> listOfLoggers) {
        this.listOfLoggers = listOfLoggers;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for ( LoggerInterface item : listOfLoggers
             ) {
            item.log(level, message);
        }
    }
}
