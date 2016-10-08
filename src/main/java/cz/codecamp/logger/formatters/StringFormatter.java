package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogLevelEnum;

/**
 * Created by vkorecky on 7.10.16.
 */
public class StringFormatter extends AbstractFormatter {
    @Override
    public String format(LogLevelEnum level, String message) {
        return String.format("[%s] [%s]: %s", level.name(), timeFormat.format(System.currentTimeMillis()), message);
    }
}
