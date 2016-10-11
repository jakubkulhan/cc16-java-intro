package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

/**
 * Created by honzapua on 9.10.2016.
 */
// chci aby zaroven soubor a stdout
abstract class MultiLogger implements LoggerInterface, PragmaticLoggerInterface {

    protected final LoggerInterface[] loggerInterfaces; // udelal jsem protected kvuli tomu aby se tam dostalo z ImperativeMultiLogger

    //predam parametry v listu tj. pole ekvivalentni LoggerInterface[] loggerInterfaces.
    public MultiLogger(LoggerInterface... loggerInterfaces) {
        this.loggerInterfaces = loggerInterfaces;
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
