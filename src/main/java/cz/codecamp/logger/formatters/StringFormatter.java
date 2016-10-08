package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogLevelEnum;

/**
 * Created by vkorecky on 7.10.16.
 */
public class StringFormatter extends AbstractFormatter {

    @Override
    public String format(LogLevelEnum level, String message, String callersClassName, String callersMethodName, int callersLineNumber) {
        return String.format("[%s] [%s] [%s] [%s] [%d]: %s", level.name(), timeFormat.format(System.currentTimeMillis()), callersClassName, callersMethodName, callersLineNumber, message);
    }
}
