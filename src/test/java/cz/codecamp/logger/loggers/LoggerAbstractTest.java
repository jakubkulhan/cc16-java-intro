package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.Message;
import cz.codecamp.logger.formaters.DummyFormatter;
import cz.codecamp.logger.formaters.JsonFormatter;
import cz.codecamp.logger.formaters.LineFormatter;
import org.junit.Test;

import static org.junit.Assert.*;

// test of abstract class, pseudo unit test
public class LoggerAbstractTest extends LoggerAbstract {
    private LoggerAbstract logger;
    private int writeCounter;
    private int writeCounterDebug;
    private int writeCounterInfo;
    private int writeCounterWarning;
    private int writeCounterError;

    public LoggerAbstractTest() {
        super();
        writeCounter = 0;
        writeCounterDebug = 0;
        writeCounterInfo = 0;
        writeCounterWarning = 0;
        writeCounterError = 0;
    }

    @Override
    protected void write(LogLevelEnum level, Message message) {
        writeCounter++;
        switch (level) {
            case DEBUG:
                writeCounterDebug++;
                break;
            case INFO:
                writeCounterInfo++;
                break;
            case WARNING:
                writeCounterWarning++;
                break;
            case ERROR:
                writeCounterError++;
                break;
        }
    }

    @Test
    public void defaultLowestLogLevel() {
        assertEquals(LogLevelEnum.INFO, getLowestLogLevel());
    }

    @Test
    public void setLowestLogLevel() {
        setLowestLogLevel(LogLevelEnum.WARNING);
        assertEquals(LogLevelEnum.WARNING, getLowestLogLevel());
    }

    @Test
    public void defaultFormatter() {
        assertTrue(getFormatter() instanceof LineFormatter);
    }

    @Test
    public void setFormatter() {
        setFormatter(new DummyFormatter());
        assertTrue(getFormatter() instanceof DummyFormatter);
    }

    @Test
    public void debugMessageNotLogging() {
        log(LogLevelEnum.DEBUG, "test message");
        assertEquals(0, writeCounter);
    }

    @Test
    public void debugMessageLogging() {
        setLowestLogLevel(LogLevelEnum.DEBUG);
        log(LogLevelEnum.DEBUG, "test message");
        assertEquals(1, writeCounter);
    }

    @Test
    public void writeDebug() {
        setLowestLogLevel(LogLevelEnum.DEBUG);
        debug("test message");
        assertEquals(1, writeCounterDebug);
    }

    @Test
    public void writeInfo() {
        info("test message");
        assertEquals(1, writeCounterInfo);
    }

    @Test
    public void writeWarning() {
        warning("test message");
        assertEquals(1, writeCounterWarning);
    }

    @Test
    public void writeError() {
        error("test message");
        assertEquals(1, writeCounterError);
    }
}
