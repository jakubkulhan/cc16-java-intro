package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.time.LocalDateTime;

public class StdoutLogger implements PragmaticLoggerInterface {

    @Override
    public void log(LogLevelEnum level, String message) {
        if (level.equals(LogLevelEnum.WARNING) || level.equals(LogLevelEnum.ERROR))
            System.out.printf(LocalDateTime.now() + " [%s] " + "%s\n", level.name(), message);
    }

    @Override
    public void log(LogLevelEnum level, LocalDateTime time, String message) {
        if (level.equals(LogLevelEnum.WARNING) || level.equals(LogLevelEnum.ERROR))
            System.out.printf(time + " [%s] " + "%s\n", level.name(), message);
    }

    @Override
    public void log(String message) {
        System.out.printf("%s\n", message);
    }

    public void log(LogLevelEnum level, String message, String className, int lineNumber) {
        if (level.equals(LogLevelEnum.WARNING) || level.equals(LogLevelEnum.ERROR))
            System.out.printf(LocalDateTime.now() + " [%s] " + className + " on the line " + lineNumber + ": %s\n", level.name(), message);
    }
}
