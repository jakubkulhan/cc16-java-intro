package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.util.List;

/**
 * Created by vkorecky on 4.10.16.
 */
public class MultiLogger extends AbstractLogger {

    private List<LoggerInterface> loggers;

    public MultiLogger(List<LoggerInterface> loggers) {
        super();
        this.loggers = loggers;
    }

    /**
     * Logger with custom formatter
     *
     * @param formatter
     */
    public MultiLogger(FormatterInterface formatter, List<LoggerInterface> loggers) {
        super(formatter);
        this.loggers = loggers;
    }

    @Override
    public void internalLog(LogLevelEnum level, String message)
    {
        // Varinata 1
//        for (LoggerInterface logger : loggers) {
//            logger.log(level, message);
//        }

        //Varianta 2
        loggers.forEach((l) -> l.log(level, message));
    }
}
