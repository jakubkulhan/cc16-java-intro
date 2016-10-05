package cz.codecamp.logger;

public interface PragmaticLoggerInterface extends LoggerInterface {
    default void debug(String message) {
        this.log(LogLevelEnum.DEBUG, message);
    }


    default void info(String message) {
        this.log(LogLevelEnum.INFO, message);
    }


    default void warning(String message) {
        this.log(LogLevelEnum.WARNING, message);
    }


    default void error(String message) {
        this.log(LogLevelEnum.ERROR, message);
    }
}
