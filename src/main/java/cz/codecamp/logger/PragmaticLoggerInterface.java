package cz.codecamp.logger;

import java.text.SimpleDateFormat;

public interface PragmaticLoggerInterface extends LoggerInterface {

    SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

    default void debug(String message) {
        log(LogLevelEnum.DEBUG, message);
    }

    default void info(String message) {
        log(LogLevelEnum.INFO, message);
    }

    default void warning(String message) {
        log(LogLevelEnum.WARNING, message);
    }

    default void error(String message) { log(LogLevelEnum.ERROR, message); }

}
