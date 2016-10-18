package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Lenovo on 09.10.2016.
 */
public class StringFormatter implements FormatterInterface {
    private boolean withDate = true;
    private SimpleDateFormat date;

    /**
     * Basic constructor sets format of log line : if
     * @param withDate, then LoggerInterface.log(...) method logs messages with current system time, else logs w/out date
     */
    public StringFormatter(boolean withDate) {
        this.withDate = withDate;
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param level - logging level from logger.LogLevelEnum
     * @param message - String log message
     * @return formatted log message with or w/out current system time.
     */
    @Override
    public String format(LogLevelEnum level, String message) {
        if(withDate) {
            return "[" + level + "] " + "[" + date.format(new Date()) + "]: " + message;
        } else {
            return "[" + level + "]: " + message;
        }
    }
}
