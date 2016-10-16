package cz.codecamp.logger.formatters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

public class LogWithoutTimeFormatter extends Formatter implements FormatterInterface {

    @Override
    public String format(LogLevelEnum level, String message) {
        StackTraceElement s = findCallerClass();
        return "[" + level.name() + "] " +  message + " Class: " + s.getClassName() + " line: " + s.getLineNumber();
    }
}
