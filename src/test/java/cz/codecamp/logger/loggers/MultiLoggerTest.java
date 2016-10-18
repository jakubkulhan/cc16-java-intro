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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by jbares on 14.10.2016.
 */
public class MultiLoggerTest {

    private List<LoggerInterface> loggers;
    private LoggerInterface logger1;
    private LoggerInterface logger2;
    private MultiLogger multiLogger;

    private final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

    @Before
    public void init() throws Exception {
        System.setOut(new PrintStream(byteStream));
        loggers = new ArrayList<>();
        logger1 = new FileLogger();
        logger2 = new StdoutLogger();
        loggers.add(logger1);
        loggers.add(logger2);
        this.multiLogger = new MultiLogger(loggers);
    }

    @Test
    public void testArraySize() throws Exception {
        assertEquals(loggers.size(), 2);
    }

    @Test
    public void testArrayContents() throws Exception {
        assertThat(loggers, hasItems(logger1,logger2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBounds() {
        loggers.get(3);
    }

    @Test
    public void testLog() throws Exception {
        multiLogger.log(LogLevelEnum.ERROR, "Real Slim Shady is causing havoc", 2);
        assertTrue(byteStream.toString().contains("Slim Shady"));
    }

    @After
    public void end() throws Exception {
        System.setOut(null);
    }

}