package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

/**
 * Created by honzapua on 9.10.2016.
 */
// chci aby zaroven soubor a stdout
abstract class MultiLogger implements LoggerInterface {

    protected final LoggerInterface[] loggerInterfaces; // udelal jsem protected kvuli tomu aby se tam dostalo z ImperativeMultiLogger

    //predam parametry v listu tj. pole ekvivalentni LoggerInterface[] loggerInterfaces.
    public MultiLogger(LoggerInterface... loggerInterfaces) {
        this.loggerInterfaces = loggerInterfaces;
    }
}
