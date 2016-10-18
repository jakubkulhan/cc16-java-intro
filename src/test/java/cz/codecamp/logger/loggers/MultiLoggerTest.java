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

public class MultiLoggerTest {
    private ByteArrayOutputStream out1;
    private ByteArrayOutputStream out2;
    private StdoutLogger logger1;
    private StderrLogger logger2;
    private MultiLogger multilogger;
    private DummyFormatter formatter;

    @Before
    public void setUp() {
        out1 = new ByteArrayOutputStream();
        out2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out1));
        System.setErr(new PrintStream(out2));
        logger1 = new StdoutLogger();
        logger2 = new StderrLogger();
        formatter = new DummyFormatter();
        logger1.setFormatter(formatter);
        logger2.setFormatter(formatter);
        multilogger = new MultiLogger(logger1, logger2);
    }

    @After
    public void cleanUp() {
        out1 = null;
        out2 = null;
        System.setOut(null);
        System.setErr(null);
        logger1 = null;
        logger2 = null;
        multilogger = null;
    }

    @Test
    public void logAllLoggersAnything() {
        multilogger.log(LogLevelEnum.INFO, "test multi");
        assertTrue(out1.toString().length() > 0 && out2.toString().length() > 0);
    }

    @Test
    public void logFirstLoggersCorrect() {
        multilogger.log(LogLevelEnum.INFO, "test multi");
        assertEquals("test multi\n", out1.toString());
    }

    @Test
    public void logSecondLoggersCorrect() {
        multilogger.log(LogLevelEnum.INFO, "test multi");
        assertEquals("test multi\n", out2.toString());
    }
}
