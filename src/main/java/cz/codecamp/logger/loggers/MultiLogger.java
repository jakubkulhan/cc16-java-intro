package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;


public class MultiLogger implements PragmaticLoggerInterface {

    private LoggerInterface [] loggerInterface;

    public MultiLogger(LoggerInterface... loggerInterface){
        this.loggerInterface = loggerInterface;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for(LoggerInterface loggerInterface: this.loggerInterface){
            loggerInterface.log(level, message);
        }
    }

    @Override
    public void debug(String message) {
        log(LogLevelEnum.DEBUG, message);
    }

    @Override
    public void info(String message) {
        log(LogLevelEnum.INFO, message);
    }

    @Override
    public void warning(String message) {
        log(LogLevelEnum.WARNING, message);
    }

    @Override
    public void error(String message) {
        log(LogLevelEnum.ERROR, message);
    }
}
