package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.formatters.StringFormatter;

import java.text.SimpleDateFormat;

public abstract class AbstractLogger implements LoggerInterface, FormatterInterface {
    private FormatterInterface formatter = new StringFormatter();

    /**
     * Logger with default StringFormatter
     */
    protected AbstractLogger() {

    }

    /**
     * Logger with custom formatter
     * @param formatter
     */
    protected AbstractLogger(FormatterInterface formatter) {
        this.formatter = formatter;
    }

    /**
     * Format message
     *
     * @param level
     * @param message
     * @return
     */
    @Override
    public String format(LogLevelEnum level, String message) {
        return getFormatter().format(level, message);
    }

    /**
     * Gets current formatter
     * @return
     */
    public FormatterInterface getFormatter() {
        return formatter;
    }

    /**
     * Sets current formatter
     * @param formatter
     */
    public void setFormatter(FormatterInterface formatter) {
        this.formatter = formatter;
    }

    /**
     * Log message
     * @param level
     * @param message
     */
    public abstract void log(LogLevelEnum level, String message);
}
