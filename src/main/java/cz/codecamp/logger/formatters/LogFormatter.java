package cz.codecamp.logger.formatters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

import java.util.Date;

public class LogFormatter extends Formatter implements FormatterInterface {

    @Override
    public String format(LogLevelEnum level, String message) {
        StackTraceElement s = findCallerClass();
        return "[" + level.name() + "] [" + format.format(new Date()) + "] " +  message + " Class: " + s.getClassName() + " line: " + s.getLineNumber();
    }
}
