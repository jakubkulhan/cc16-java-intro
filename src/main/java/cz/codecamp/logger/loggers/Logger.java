package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

public abstract class Logger implements LoggerInterface {

    private LogLevelEnum minLogLevel;

    public Logger() {
       minLogLevel = LogLevelEnum.DEBUG;
    }

    public LogLevelEnum getMinLogLevel() {
        return minLogLevel;
    }

    public void setLevel(LogLevelEnum minLogLevel) {
        this.minLogLevel = minLogLevel;
    }

}
