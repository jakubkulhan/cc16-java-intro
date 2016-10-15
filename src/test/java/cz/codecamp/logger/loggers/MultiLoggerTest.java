package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martin on 15.10.2016.
 */
public class MultiLoggerTest {

    private List<LoggerInterface> loggers;
    private LoggerInterface logger1;
    private LoggerInterface logger2;
    private MultiLogger multiLogger;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        loggers = new ArrayList<LoggerInterface>();
        logger1 = new StdoutLogger();
        logger2 = new StdoutLogger();
        loggers.add(logger1);
        loggers.add(logger2);
        this.multiLogger = new MultiLogger(loggers);
    }

    @Test
    public void testMetodyLog() throws Exception {
        multiLogger.log(LogLevelEnum.INFO, "Test message", 2);
        boolean actual = true;
        boolean expected = outContent.toString().contains("INFO");
        assertEquals(actual, expected);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

}