package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;

/**
 * Created by bmandzhi
 */
public class StdoutLoggerTest {

    StdoutLogger stdoutLogger;
    LogLevelEnum logLevelEnum;
    LocalDateTime localDateTime = LocalDateTime.now();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        stdoutLogger = new StdoutLogger();
        logLevelEnum = LogLevelEnum.WARNING;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void log1() throws Exception {
        stdoutLogger.log(logLevelEnum, localDateTime, anyString());
        assertEquals(localDateTime + " [" + logLevelEnum + "] " + anyString() + "\n", outContent.toString());
    }

    @Test
    public void log2() throws Exception {
        stdoutLogger.log(anyString());
        assertEquals(anyString() + "\n", outContent.toString());
    }
}