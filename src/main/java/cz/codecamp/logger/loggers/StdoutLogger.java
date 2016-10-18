package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.util.Date;

public class StdoutLogger implements PragmaticLoggerInterface {

    @Override
    public void log(LogLevelEnum level, String message) {
        System.out.printf(formatter.format(level, message));
    }

}
