package cz.codecamp.logger.filters;

import cz.codecamp.logger.LogLevelEnum;

public class LogLevelFilter implements LogMessageFilter {

    private final LogLevelEnum levelThreshold;

    public LogLevelFilter(LogLevelEnum levelThreshold) {
        this.levelThreshold = levelThreshold;
    }

    @Override
    public boolean test(LogLevelEnum logLevel, String logMessage) {
        return levelThreshold.getSeverity() <= logLevel.getSeverity();
    }
}
