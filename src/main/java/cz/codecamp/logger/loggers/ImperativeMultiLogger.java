package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.util.List;

/**
 * Created by Lenovo on 06.10.2016.
 */
public class ImperativeMultiLogger implements LoggerInterface, PragmaticLoggerInterface {
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

    @Override
    public void debug(String message) {
        for (LoggerInterface logger : list) {
            logger.log(LogLevelEnum.DEBUG, message);
        }
    }

    @Override
    public void info(String message) {
        for (LoggerInterface logger : list) {
            logger.log(LogLevelEnum.INFO, message);
        }
    }

    @Override
    public void warning(String message) {
        for (LoggerInterface logger : list) {
            logger.log(LogLevelEnum.WARNING, message);
        }
    }

    @Override
    public void error(String message) {
        for (LoggerInterface logger : list) {
            logger.log(LogLevelEnum.ERROR, message);
        }
    }
}

