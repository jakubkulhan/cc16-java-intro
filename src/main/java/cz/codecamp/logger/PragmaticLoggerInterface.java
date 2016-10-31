package cz.codecamp.logger;

public interface PragmaticLoggerInterface extends LoggerInterface {
    default void debug(String message, int  minLogLevel) {
        log(LogLevelEnum.DEBUG, message, minLogLevel);
    }

    default void info(String message, int  minLogLevel) {log(LogLevelEnum.INFO, message,minLogLevel);
    }

    default void warning(String message, int  minLogLevel) {
        log(LogLevelEnum.WARNING, message,minLogLevel);
    }

    default void error(String message, int  minLogLevel) { log(LogLevelEnum.ERROR, message,minLogLevel);  }
}
