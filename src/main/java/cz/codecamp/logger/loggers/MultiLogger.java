package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MultiLogger implements LoggerInterface {

    private final List<LoggerInterface> loggers;

    public MultiLogger(LoggerInterface... loggers) {
        if (loggers == null) {
            throw new IllegalArgumentException();
        }
        this.loggers = Collections.unmodifiableList(
                Arrays.stream(loggers).collect(Collectors.toList()));
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        loggers.forEach(l -> l.log(level, message));
    }
}
