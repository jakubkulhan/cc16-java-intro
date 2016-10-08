package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

public class StderrLogger extends AbstractLogger {

    /**
     * Logger with default StringFormatter
     */
    public StderrLogger() {
        super();
    }

    /**
     * Logger with custom formatter
     *
     * @param formatter
     */
    public StderrLogger(FormatterInterface formatter) {
        super(formatter);
    }

    @Override
    public void internalLog(LogLevelEnum level, String message, String callersClassName, String callersMethodName, int callersLineNumber) {
        System.err.println(format(level, message, callersClassName, callersMethodName, callersLineNumber));
    }
}
