package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

/**
 * Created by honzapua on 9.10.2016.
 */
public class FunctionalMultiLogger extends MultiLogger {

    public FunctionalMultiLogger(LoggerInterface... loggerInterfaces) { //zpropaguje konstruktor predka
        super(loggerInterfaces);
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for (LoggerInterface loggerInterface : loggerInterfaces){
            loggerInterface.log(level, message);
        }
    }


}
