package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.util.List;

/**
 * Created by Lenovo on 06.10.2016.
 */
public class ImperativeMultiLogger implements LoggerInterface {
    private List<LoggerInterface> list;

    public ImperativeMultiLogger(List<LoggerInterface> list) {
        this.list = list;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for (LoggerInterface logger : list) {
            logger.log(level, message);
        }
    }
}

