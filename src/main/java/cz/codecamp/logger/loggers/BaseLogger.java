package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.formatters.SimpleFormatter;

import java.io.IOException;

/**
 * Created by micha on 05.10.2016.
 */
public abstract class BaseLogger implements LoggerInterface {
    private FormatterInterface formatter;
    private LogLevelEnum minLogLevel;

    public BaseLogger() {
        this.formatter = new SimpleFormatter();
        this.minLogLevel = LogLevelEnum.DEBUG;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        if (minLogLevel.isLowerOrEqualTo(level)) { // task #4
            logFormatted(level, message, formatter.format(level, message)); // task #5
        }
    }

    @Override
    public void setFormatter(FormatterInterface formatter) {
        this.formatter = formatter;
    }

    @Override
    public void setMinLogLevel(LogLevelEnum minLogLevel) {
        this.minLogLevel = minLogLevel;
    }

    protected FormatterInterface getFormatter() {
        return formatter;
    }

    protected LogLevelEnum getMinLogLevel() {
        return minLogLevel;
    }

    protected abstract void logFormatted(LogLevelEnum level, String originalMessage, String formattedMessage);
}
