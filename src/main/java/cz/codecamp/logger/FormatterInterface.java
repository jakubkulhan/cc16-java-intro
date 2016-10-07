package cz.codecamp.logger;

import java.text.SimpleDateFormat;

public interface FormatterInterface {
    /**
     * Format message
     * @param level
     * @param message
     * @return
     */
    String format(LogLevelEnum level, String message);
}
