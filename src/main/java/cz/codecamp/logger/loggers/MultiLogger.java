package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;
import java.util.List;

public class MultiLogger implements LoggerInterface, PragmaticLoggerInterface {

    private List<LoggerInterface> loggers;

    public MultiLogger(List<LoggerInterface> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for (LoggerInterface l : loggers) {
            l.log(level, message);
            // varianta 2
            // loggers.forEach((l) -> l.log(level, message));
        }
    }
}
