package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

/**
 * Created by lenka.salacova on 10/15/2016.
 */
public abstract class AbstractLogger implements LoggerInterface, PragmaticLoggerInterface {


    @Override
    public abstract void log(LogLevelEnum level, String message);

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
}
