package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.util.Date;

public class StdoutLogger extends AbstractLogger{

    /**
     * Logger with default StringFormatter
     */
    public StdoutLogger() {
        super();
    }

    /**
     * Logger with custom formatter
     *
     * @param formatter
     */
    public StdoutLogger(FormatterInterface formatter) {
        super(formatter);
    }

    @Override
    public void internalLog(LogLevelEnum level, String message) {
        System.out.println(format(level, message));
    }
}
