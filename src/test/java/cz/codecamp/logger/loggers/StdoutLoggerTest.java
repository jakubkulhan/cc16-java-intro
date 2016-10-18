package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.formaters.DummyFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StdoutLoggerTest {
    private ByteArrayOutputStream out;
    private StdoutLogger logger;
    private DummyFormatter formatter;

    @Before
    public void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        logger = new StdoutLogger();
        formatter = new DummyFormatter();
        logger.setFormatter(formatter);
    }

    @After
    public void cleanUp() {
        out = null;
        System.setOut(null);
        logger = null;
        formatter = null;
    }

    @Test
    public void logAnything() {
        logger.log(LogLevelEnum.INFO, "test");
        assertTrue(out.toString().length() > 0);
    }

    @Test
    public void logCorrectMessage() {
        logger.log(LogLevelEnum.INFO, "test");
        assertEquals("test\n", out.toString());
    }
}
