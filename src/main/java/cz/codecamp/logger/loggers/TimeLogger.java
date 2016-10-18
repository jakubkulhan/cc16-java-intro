package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.SimpleDateFormat;

public class TimeLogger implements FormatterInterface {

    private DateTime now = new org.joda.time.DateTime();
    private String pattern = "[yyyy-MM-dd HH:mm:ss]";
    private org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
    private String formatted;
    //private SimpleDateFormat simpleDateFormat;


    public TimeLogger(){
        //simpleDateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
        formatted = formatter.print(now);
    }

    @Override
    public String format(LogLevelEnum level, String message) {
       return "[" + level + "] " + formatted + " [" + message + "]";
    }
}
