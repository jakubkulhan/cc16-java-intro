package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;
import cz.codecamp.logger.formatters.LogFormatter;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Filip Ocelka on 16.10.2016, filip.ocelka@gmail.com
 */
public class MultiLoggerTest {

    private PragmaticLoggerInterface logger;
    private PrintStream out;
    private LocalDateTime now;
    private DateTimeFormatter dateTimeFormatter;

    @Before
    public void setUp() throws Exception {
        LoggerInterface stdoutLogger = new StdoutLogger();
        LoggerInterface printStreamLogger = new PrintStreamLogger(System.out);

        List<LoggerInterface> loggers = new ArrayList<>();

        loggers.add(stdoutLogger);
        loggers.add(printStreamLogger);

        logger = new MultiLogger(loggers);

        out = mock(PrintStream.class);
        System.setOut(out);
        now = LocalDateTime.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Test
    public void testOutputOdStdOutLoggerWithUnspecifiedFormatter() throws Exception {
        logger.error("test");
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result
        verify(out, times(2)).println("[ERROR] [" + dateTimeFormatter.format(now) + "] test Class: " + ste[1].getClassName() + " line: " + (ste[1].getLineNumber()-1));
    }

    @Test
    public void testOutputOdStdOutLoggerWithGivenFormatter() throws Exception {
        FormatterInterface logFormatter = new LogFormatter();
        logger.log(LogLevelEnum.INFO, "test", logFormatter);
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result
        verify(out, times(2)).println("[INFO] [" + dateTimeFormatter.format(now) + "] test Class: " + ste[1].getClassName() + " line: " + (ste[1].getLineNumber()-1));
    }
}