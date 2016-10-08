package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;
import cz.codecamp.logger.formatters.StringFormatter;

public abstract class AbstractLogger implements LoggerInterface, PragmaticLoggerInterface, FormatterInterface {
    
    private FormatterInterface formatter = new StringFormatter();
    private LogLevelEnum threshold = LogLevelEnum.DEBUG;

    /**
     * Logger with default StringFormatter
     */
    protected AbstractLogger() {
        
    }

    /**
     * Logger with custom formatter
     *
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
    public String format(LogLevelEnum level, String message, String callersClassName, String callersMethodName, int callersLineNumber) {
        return getFormatter().format(level, message, callersClassName, callersMethodName, callersLineNumber);
    }

    /**
     * Log message
     *
     * @param level
     * @param message
     */
    @Override
    public void log(LogLevelEnum level, String message) {
        if (level.getLevel() >= getThreshold().getLevel()) {


            String callersClassName = null;
            String callersMethodName = null;
            int callersLineNumber = -1;
            for (int i = 1; i<Thread.currentThread().getStackTrace().length; i++) {
                callersClassName = Thread.currentThread().getStackTrace()[i].getClassName();
                if ((!callersClassName.startsWith("cz.codecamp.logger.loggers")) && (!callersClassName.startsWith("cz.codecamp.logger.formatters"))) {
                    callersMethodName = Thread.currentThread().getStackTrace()[i].getMethodName();
                    callersLineNumber = Thread.currentThread().getStackTrace()[i].getLineNumber();
                    break;
                }
            }
            internalLog(level, message, callersClassName, callersMethodName, callersLineNumber);
        }
    }
    
    public abstract void internalLog(LogLevelEnum level, String message, String callersClassName, String callersMethodName, int callersLineNumber);

    /**
     * Gets current formatter
     *
     * @return
     */
    public FormatterInterface getFormatter() {
        return formatter;
    }

    /**
     * Sets current formatter
     *
     * @param formatter
     */
    public void setFormatter(FormatterInterface formatter) {
        this.formatter = formatter;
    }

    /**
     * Gets threshold
     *
     * @return
     */
    @Override
    public LogLevelEnum getThreshold() {
        return threshold;
    }

    /**
     * Sets threshold
     *
     * @param threshold
     */
    @Override
    public void setThreshold(LogLevelEnum threshold) {
        this.threshold = threshold;
    }
}
