package cz.codecamp.logger.formatters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

/**
 * Created by lenka.salacova on 10/15/2016.
 */
public class NoDateFormatter implements FormatterInterface{

    @Override
    public String format(LogLevelEnum level, String message) {
        return "[" + level + "] " + message;
    }
}
