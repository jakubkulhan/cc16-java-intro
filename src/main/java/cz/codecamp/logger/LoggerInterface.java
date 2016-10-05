package cz.codecamp.logger;

import java.text.SimpleDateFormat;

public interface LoggerInterface {
    public final String STRING_FORMATER = "YYYY-MM-DDD hh:mm:ss";
    public final SimpleDateFormat format = new SimpleDateFormat(STRING_FORMATER);

    void log(LogLevelEnum level, String message);
}
