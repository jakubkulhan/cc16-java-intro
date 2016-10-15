package cz.codecamp.logger;

public interface PragmaticLoggerInterface extends LoggerInterface {

    default void debug(String message) {}

    default void info(String message) {}

    default void warning(String message) {}

    default void error(String message) {}

}
