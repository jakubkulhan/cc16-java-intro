package cz.codecamp.logger;

public interface FormatterInterface {
    String format(LogLevelEnum level, String message);
}
