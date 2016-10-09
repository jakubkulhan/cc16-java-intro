package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

/**
 * Created by honzapua on 9.10.2016.
 */
public class ImperativeMultiLogger extends MultiLogger {
    public ImperativeMultiLogger(LoggerInterface... loggerInterfaces) {
        super(loggerInterfaces);
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for (int i = 0; i < loggerInterfaces.length; i++) {
            LoggerInterface loggerInterface = loggerInterfaces[i];
            loggerInterface.log(level, message);
        }
    }
}
