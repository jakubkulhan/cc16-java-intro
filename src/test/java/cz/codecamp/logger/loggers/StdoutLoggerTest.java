package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.PragmaticLoggerInterface;
import cz.codecamp.logger.formatters.LogFormatter;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;

public class StdoutLoggerTest {

    private PragmaticLoggerInterface logger;
    private PrintStream out;
    private LocalDateTime now;
    private DateTimeFormatter dateTimeFormatter;

    @Before
    public void setUp() throws Exception {
        logger = new StdoutLogger();
        out = mock(PrintStream.class);
        System.setOut(out);
        now = LocalDateTime.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Test
    public void testOutputOdStdOutLoggerWithUnspecifiedFormatter() throws Exception {
        logger.info("test");
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result
        verify(out).println("[INFO] [" + dateTimeFormatter.format(now) + "] test Class: " + ste[1].getClassName() + " line: " + (ste[1].getLineNumber()-1));
    }

    @Test
    public void testOutputOdStdOutLoggerWithGivenFormatter() throws Exception {
        FormatterInterface logFormatter = new LogFormatter();
        logger.log(LogLevelEnum.INFO, "test", logFormatter);
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result
        verify(out).println("[INFO] [" + dateTimeFormatter.format(now) + "] test Class: " + ste[1].getClassName() + " line: " + (ste[1].getLineNumber()-1));
    }

    @Test
    public void testIfNothingIsWrittenToStdoutWhenLevelIsSetToErrorButMessageIsWarning() throws Exception {
        logger.setLevel(LogLevelEnum.ERROR);
        logger.warning("test");
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result
        verify(out, never()).println("[WARNING] [" + dateTimeFormatter.format(now) + "] test Class: " + ste[1].getClassName() + " line: " + (ste[1].getLineNumber()-1));
    }

    /*
    @Test
    public void testOutputOdStdOutLoggerWithGivenJSONFormatter() throws Exception {
        //Date mockedDate = mock(Date.class);
        //when(mockedDate.getTime()).thenReturn(1476562551694L);
        FormatterInterface logFormatter = new LogJSONFormatter();
        logger.log(LogLevelEnum.INFO, "test1", logFormatter);
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result
        verify(out).println("{\"lvl\":\"INFO\",\"ts\":" + new Date().getTime() + ",\"msg\":\"test2\",\"className\":\"" + ste[1].getClassName() + "\",\"lineNumber\":" + (ste[1].getLineNumber() - 1) + "}");
    }
*/
}