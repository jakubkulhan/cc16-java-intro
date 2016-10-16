package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.util.List;

public class MultiLogger extends Logger implements LoggerInterface, PragmaticLoggerInterface {

    private List<LoggerInterface> loggers;

    public MultiLogger(List<LoggerInterface> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        /*
        for (LoggerInterface logger : loggers) {
            logger.log(level, message);
        }
        */
        if (level.ordinal() >= getMinLogLevel().ordinal()) {
            loggers.forEach((l) -> l.log(level, message));
        }
    }

    @Override
    public void log(LogLevelEnum level, String message, FormatterInterface formatter) {
        if (level.ordinal() >= getMinLogLevel().ordinal()) {
            loggers.forEach((l) -> l.log(level, message, formatter));
        }
    }
}
