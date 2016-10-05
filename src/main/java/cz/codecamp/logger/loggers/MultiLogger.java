package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import sun.rmi.runtime.Log;


public class MultiLogger implements LoggerInterface {

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
}
