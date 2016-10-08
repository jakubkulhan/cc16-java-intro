package cz.codecamp.logger;

public interface PragmaticLoggerInterface extends LoggerInterface {

    /**
     * Logs DEBUG message
     *
     * @param message
     */
    default void debug(String message) {
        log(LogLevelEnum.DEBUG, message);
    }

    /**
     * Logs INFO message
     *
     * @param message
     */
    default void info(String message) {
        log(LogLevelEnum.INFO, message);
    }

    /**
     * Logs WARNING message
     *
     * @param message
     */
    default void warning(String message) {
        log(LogLevelEnum.WARNING, message);
    }

    /**
     * Logs ERROR message
     *
     * @param message
     */
    default void error(String message) {
        log(LogLevelEnum.ERROR, message);
    }
}
