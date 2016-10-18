package cz.codecamp.logger.loggers;

import cz.codecamp.logger.*;
import cz.codecamp.logger.formaters.LineFormatter;

/**
 * Created by jorat on 16.10.16.
 */
public abstract class LoggerAbstract implements LoggerInterface, PragmaticLoggerInterface {
    protected FormatterInterface formatter;
    protected LogLevelEnum lowestLogLevel;

    LoggerAbstract() {
        formatter = new LineFormatter();
        this.lowestLogLevel = LogLevelEnum.INFO;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        if (level.isHigherOrEqual(lowestLogLevel)) {
            Message messageEnvelope = new Message(level, message, LoggerAbstract.class.getName());
            write(level, messageEnvelope);
        }
    }

    protected abstract void write(LogLevelEnum level, Message message);

    public LogLevelEnum getLowestLogLevel() {
        return this.lowestLogLevel;
    }

    public LoggerAbstract setLowestLogLevel(LogLevelEnum value) {
        lowestLogLevel = value;
        return this;
    }

    public FormatterInterface getFormatter() {
        return this.formatter;
    }

    public void setFormatter(FormatterInterface formatter) {
        this.formatter = formatter;
    }
}
