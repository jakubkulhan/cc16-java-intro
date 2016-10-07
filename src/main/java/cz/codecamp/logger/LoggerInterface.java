package cz.codecamp.logger;

import java.text.SimpleDateFormat;

public interface LoggerInterface {
    void log(LogLevelEnum level, String message);
}