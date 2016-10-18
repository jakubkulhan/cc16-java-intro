package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.Message;
import cz.codecamp.logger.PragmaticLoggerInterface;

public class MultiLogger implements LoggerInterface, PragmaticLoggerInterface {

    protected LoggerInterface[] loggerInterfaces;

    public MultiLogger(LoggerInterface... loggerInterfaces) {
        this.loggerInterfaces = loggerInterfaces;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for (LoggerInterface logger : loggerInterfaces) {
            logger.log(level, message);
        }
    }

}
