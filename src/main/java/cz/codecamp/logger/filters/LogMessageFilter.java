package cz.codecamp.logger.filters;

import cz.codecamp.logger.LogLevelEnum;

public interface LogMessageFilter {
    boolean test(LogLevelEnum logLevel, String logMessage);
}
