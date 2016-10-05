package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.util.Date;

public class StdoutLogger implements PragmaticLoggerInterface {

    @Override
    public void log(LogLevelEnum level, String message) {
        System.out.printf("[%s]: [%s] %s\n", level.name(), dt.format(new Date()), message);
    }

    @Override
    public void debug(String message) {
        System.out.printf("[%s]: %s\n", LogLevelEnum.DEBUG.name(), message);
    }

    @Override
    public void info(String message) {
        System.out.printf("[%s]: %s\n", LogLevelEnum.INFO.name(), message);
    }

    @Override
    public void warning(String message) {
        System.out.printf("[%s]: %s\n", LogLevelEnum.WARNING.name(), message);
    }

    @Override
    public void error(String message) {
        System.out.printf("[%s]: %s\n", LogLevelEnum.ERROR.name(), message);
    }
}
