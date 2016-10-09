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
    private SimpleDateFormat dateFormat;

    public StringFormatter(boolean withDate) {
        this.withDate = withDate;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String format(LogLevelEnum level, String message) {
        if(withDate) {
            return "[" + level + "] " + "[" + dateFormat.format(new Date()) + "]: " + message;
        } else {
            return "[" + level + "]: " + message;
        }
    }
}
