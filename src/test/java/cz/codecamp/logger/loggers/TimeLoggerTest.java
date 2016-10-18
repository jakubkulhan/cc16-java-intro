package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Created by dasklar on 18. 10. 2016.
 */
public class TimeLoggerTest {
    private TimeLogger timeLogger;
    private String message;
    private DateTime now = new org.joda.time.DateTime();
    private String pattern;
    private org.joda.time.format.DateTimeFormatter formatter;
    private String formatted;
    private LogLevelEnum log;


    @Before
    public void setUp() throws Exception {
    timeLogger = new TimeLogger();
    message = "warning test";
    pattern = "[yyyy-MM-dd HH:mm:ss]";
    formatter = DateTimeFormat.forPattern(pattern);
    formatted = formatter.print(now);
        log = LogLevelEnum.WARNING;

    }

    @Test
    public void format() throws Exception {
    assertEquals(timeLogger.format(log,message),"[" + log + "] " + formatted + " [" + message + "]");
    }

}