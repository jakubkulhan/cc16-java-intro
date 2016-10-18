package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

public abstract class Logger implements LoggerInterface, PragmaticLoggerInterface {

    LogLevelEnum levelBound = LogLevelEnum.DEBUG;
    boolean JSON = false;

    public void setBound(LogLevelEnum fromLevel) {
        this.levelBound = fromLevel;
    }

    LogLevelEnum getBound() {
        return levelBound;
    }

    public void toJSON() {
        this.JSON = true;
    }
}
