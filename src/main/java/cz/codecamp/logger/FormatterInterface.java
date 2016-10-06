package cz.codecamp.logger;

public interface FormatterInterface {
    String format(LogLevelEnum level, String message);

    String format(LogLevelEnum level, String message, String callingClass, int callingLineNumber);
}
