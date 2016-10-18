package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.time.LocalDateTime;
import java.util.List;

public class MultiLogger implements PragmaticLoggerInterface {

    private List<PragmaticLoggerInterface> loggers;

    public MultiLogger(List<PragmaticLoggerInterface> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        loggers.forEach(logger -> logger.log(level, message));
    }

    @Override
    public void log(LogLevelEnum level, LocalDateTime time, String message) {
        if (level.equals(LogLevelEnum.WARNING) || level.equals(LogLevelEnum.ERROR))
            loggers.forEach(logger -> logger.log(level, time, message));
    }

    @Override
    public void log(String message) {
        loggers.forEach(logger -> logger.log(message));
    }

    @Override
    public void close() {
        loggers.forEach(logger -> logger.close());
    }
}
