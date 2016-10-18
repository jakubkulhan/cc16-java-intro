package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.sql.Time;

public class StdoutLogger implements PragmaticLoggerInterface {

    private WithoutTimeLogger withoutTimeLogger= new WithoutTimeLogger();

    public StdoutLogger(){}

    @Override
    public void log(LogLevelEnum level, String message) {
        System.out.printf("%s\n", withoutTimeLogger.format(level, message));
    }


}
