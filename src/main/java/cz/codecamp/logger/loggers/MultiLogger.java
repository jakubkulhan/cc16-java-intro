package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;


public class MultiLogger implements LoggerInterface, PragmaticLoggerInterface {

    private List<LoggerInterface> loggers;

    public MultiLogger(List<LoggerInterface> loggers) {
        this.loggers = loggers;
    }

    public void log(LogLevelEnum level, String message, int minimumLevel)
    {
        for (LoggerInterface logger : loggers) {
            logger.log(level, message, minimumLevel);
        }

    }
}