package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

public class WithoutTimeLogger implements FormatterInterface {

    public WithoutTimeLogger(){}

    @Override
    public String format(LogLevelEnum level, String message) {
       return "[" + level + "] " + "[" + message + "]";
    }
}
