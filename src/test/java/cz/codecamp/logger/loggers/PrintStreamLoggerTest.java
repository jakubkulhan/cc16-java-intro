package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;
import cz.codecamp.logger.formatters.LogFormatter;
import cz.codecamp.logger.formatters.LogJSONFormatter;
import cz.codecamp.logger.formatters.LogWithoutTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Filip Ocelka on 15.10.2016, filip.ocelka@gmail.com
 */
public class PrintStreamLoggerTest {

    private PragmaticLoggerInterface logger;
    private PrintStream out;
    private LocalDateTime now;
    private DateTimeFormatter dateTimeFormatter;

    @Before
    public void setUp() throws Exception {
        logger = new PrintStreamLogger(System.out);
        out = mock(PrintStream.class);
        System.setOut(out);
        now = LocalDateTime.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Test
    public void testOutputOdStdOutLoggerWithUnspecifiedFormatter() throws Exception {
        logger.debug("test");
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result
        verify(out).println("[DEBUG] [" + dateTimeFormatter.format(now) + "] test Class: " + ste[1].getClassName() + " line: " + (ste[1].getLineNumber()-1));
    }

    @Test
    public void testOutputOdStdOutLoggerWithGivenFormatter() throws Exception {
        FormatterInterface logFormatter = new LogWithoutTimeFormatter();
        logger.log(LogLevelEnum.INFO, "test", logFormatter);
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result
        verify(out).println("[INFO] test Class: " + ste[1].getClassName() + " line: " + (ste[1].getLineNumber()-1));
    }

    /* To do -> find way how to test timestamp
    @Test
    public void testOutputOdStdOutLoggerWithGivenJSONFormatter() throws Exception {
        FormatterInterface logFormatter = new LogJSONFormatter();
        logger.log(LogLevelEnum.INFO, "test", logFormatter);
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result
        verify(out).println("{\"lvl\":\"INFO\",\"ts\":" + new Date().getTime() + ",\"msg\":\"test\",\"className\":\"" + ste[1].getClassName() + "\",\"lineNumber\":" + (ste[1].getLineNumber() - 1) + "}");
    }
    */
}