package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.PrintStream;

public class MultiLogger implements LoggerInterface {

    protected LoggerInterface[] loggerInterfaces;

    public MultiLogger(LoggerInterface... loggerInterfaces) {
        this.loggerInterfaces = loggerInterfaces;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for (LoggerInterface logger : this.loggerInterfaces) {
            logger.log(level, message);
        }
    }

}
