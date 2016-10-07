package cz.codecamp.logger.formatters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

import java.util.Date;

/**
 * Created by vkorecky on 7.10.16.
 */
public class StringFormatter implements FormatterInterface {
    @Override
    public String format(LogLevelEnum level, String message) {
        return String.format("[%s] [%s]: %s", level.name(), TIME_FORMAT.format(new Date()), message);
    }
}
