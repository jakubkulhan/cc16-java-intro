package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LogMessage;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;
import java.util.Date;

public class StderrLogger extends Logger implements LoggerInterface, PragmaticLoggerInterface {

    @Override
    public void log(LogLevelEnum level, String message)
    {
        if (level.getLevel() >= getBound().getLevel()) {
            System.err.println(new LogMessage(level, message, new Date(), JSON));
        }
    }

}
