package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

public class StdoutLogger implements LoggerInterface, PragmaticLoggerInterface {
    private StringFormatter stringFormatter;
    private MessageFilter msgFltr;
    public StdoutLogger() {
        stringFormatter = new StringFormatter(true);
        msgFltr = new MessageFilter(LogLevelEnum.WARNING);
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        if(msgFltr.send(level)) System.out.printf("%s\n", stringFormatter.format(level, message));
    }

    @Override
    public void debug(String message) {
        System.out.printf("[%s]: %s\n", LogLevelEnum.DEBUG, message);
    }

    @Override
    public void info(String message) {
        System.out.printf("[%s]: %s\n", LogLevelEnum.INFO, message);
    }

    @Override
    public void warning(String message) {
        System.out.printf("[%s]: %s\n", LogLevelEnum.WARNING, message);
    }

    @Override
    public void error(String message) {
        System.out.printf("%s\n", stringFormatter.format(LogLevelEnum.ERROR, message));
    }
}
