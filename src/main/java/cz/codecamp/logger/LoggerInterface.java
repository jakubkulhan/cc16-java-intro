package cz.codecamp.logger;

public interface LoggerInterface {
    /**
     * Loguje pouze level, ktery je vetsi nez threshold.
     *
     * @param level
     * @param message
     */
    void log(LogLevelEnum level, String message);
}
