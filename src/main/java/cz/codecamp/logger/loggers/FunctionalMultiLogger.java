package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.util.List;

/**
 * Created by Lenovo on 08.10.2016.
 */
public class FunctionalMultiLogger implements LoggerInterface {
    private List<LoggerInterface> list;

    public FunctionalMultiLogger(List<LoggerInterface> list) {
        this.list = list;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        list.forEach((loggerInterface) -> log(level, message));
    }
}
