package cz.codecamp.logger;

import java.text.SimpleDateFormat;

public interface LoggerInterface {

    void log(LogLevelEnum level, String message);
    void log(LogLevelEnum level, String message, FormatterInterface formatter);
    void setLevel(LogLevelEnum level);
}
