package cz.codecamp.logger;

public interface PragmaticLoggerInterface extends LoggerInterface {
    default void debug(String message) {
        log(LogLevelEnum.DEBUG, message);
    }

    default void info(String message) {
        log(LogLevelEnum.INFO, message);
    }

    default void warning(String message) {
        log(LogLevelEnum.WARNING, message);
    }

    default void error(String message) {
        log(LogLevelEnum.ERROR, message);
    }
}
