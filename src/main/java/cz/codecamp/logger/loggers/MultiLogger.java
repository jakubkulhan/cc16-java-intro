package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by vkorecky on 4.10.16.
 */
public class MultiLogger implements LoggerInterface {

    private List<LoggerInterface> loggers;

    public MultiLogger(List<LoggerInterface> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void log(LogLevelEnum level, String message)
    {
        // Varinata 1
        for (LoggerInterface logger : loggers) {
            logger.log(level, message);
        }

        //Varianta 2
        loggers.forEach((l) -> l.log(level, message));
    }
}
