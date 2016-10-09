package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

/**
 * Created by honzapua on 9.10.2016.
 */
// chci aby zaroven soubor a stdout
public class MultiLogger implements LoggerInterface {

    private final LoggerInterface[] loggerInterfaces;

    @Override
    public void log(LogLevelEnum level, String message) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    //predam parametry v listu tj. pole ekvivalentni LoggerInterface[] loggerInterfaces.
    public MultiLogger(LoggerInterface... loggerInterfaces) {
        this.loggerInterfaces = loggerInterfaces;
    }
}
