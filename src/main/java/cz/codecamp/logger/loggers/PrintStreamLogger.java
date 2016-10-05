package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;


public class PrintStreamLogger implements LoggerInterface{

    private LoggerInterface loggerInterface;

    public PrintStreamLogger(LoggerInterface loggerInterface){
        this.loggerInterface = loggerInterface;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        loggerInterface.log(level, message);
    }
}
