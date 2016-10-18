package cz.codecamp.logger;

public interface LoggerInterface {
    /**
     * Loguje pouze level, ktery je vetsi nez threshold.
     * Loguje pouze kdyz to ma smysl, tj. je definovany stream
     *
     * @param level
     * @param message
     */
    void log(LogLevelEnum level, String message);
}
