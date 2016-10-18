package cz.codecamp.logger;

public interface PragmaticLoggerInterface extends LoggerInterface {
    default void debug(String message) {
        System.out.println("debug : " + message);
    }

    default void info(String message) {
        System.out.println("info message: " + message);
    }

    default void warning(String message) {
        System.out.println("warning message: " + message);
    }

    default void error(String message) {
        System.out.println("error message: " + message);
    }
}
