package cz.codecamp.logger;

public interface FormatterInterface {

    /**
     * Format message
     *
     * @param level
     * @param message
     * @param callersClassName
     * @param callersMethodName
     * @param callersLineNumber
     * @return
     */
    String format(LogLevelEnum level, String message, String callersClassName, String callersMethodName, int callersLineNumber);
}
