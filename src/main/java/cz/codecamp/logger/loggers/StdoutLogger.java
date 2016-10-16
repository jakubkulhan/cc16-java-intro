package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;
import cz.codecamp.logger.formatters.LogFormatter;

public class StdoutLogger extends Logger implements LoggerInterface, PragmaticLoggerInterface {


    private FormatterInterface defaultFormatter;

    public StdoutLogger() {
        defaultFormatter = new LogFormatter();
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        if (level.ordinal() >= getMinLogLevel().ordinal()) {
            System.out.println(defaultFormatter.format(level, message));
        }
    }

    @Override
    public void log(LogLevelEnum level, String message, FormatterInterface formatter) {
        if (level.ordinal() >= getMinLogLevel().ordinal()) {
            System.out.println(formatter.format(level, message));
        }

    }

}
