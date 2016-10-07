package cz.codecamp.logger;

import java.text.SimpleDateFormat;

public interface FormatterInterface {
    final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("YYYY-MM-DDD hh:mm:ss");

    /**
     * Format message
     * @param level
     * @param message
     * @return
     */
    String format(LogLevelEnum level, String message);
}
