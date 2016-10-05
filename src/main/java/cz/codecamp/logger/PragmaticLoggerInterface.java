package cz.codecamp.logger;

import java.text.SimpleDateFormat;

public interface PragmaticLoggerInterface extends LoggerInterface {

    SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

    void debug(String message);
    void info(String message);
    void warning(String message);
    void error(String message);
}
