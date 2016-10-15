package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martin on 14.10.2016.
 * Testování výstupu do System.out.println()
 */
public class StdoutLoggerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private StdoutLogger logger;


    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        this.logger = new StdoutLogger();
    }

    @Test
    public void printLogVraciTruePoZalogovaniMessage() throws Exception {
        boolean actual = logger.printLog(LogLevelEnum.INFO, "test message");
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void logMinLogLevel_1_obsahuje_INFO() throws Exception{
        logger.log(LogLevelEnum.INFO, "Test message", 1);
        boolean actual = true;
        boolean expected = outContent.toString().contains("INFO");
        assertEquals(actual, expected);
    }

    @Test
    public void logMinLogLevel_2_obsahuje_INFO() throws Exception{
        logger.log(LogLevelEnum.INFO, "Test message", 2);
        boolean actual = true;
        boolean expected = outContent.toString().contains("INFO");
        assertEquals(actual, expected);
    }

    @Test
    public void logMinLogLevel_3_neobsahuje_INFO() throws Exception{
        logger.log(LogLevelEnum.INFO, "Test message", 3);
        boolean actual = false;
        boolean expected = outContent.toString().contains("INFO");
        assertEquals(actual, expected);
    }

    @Test
    public void logMinLogLevel_4_neobsahuje_INFO() throws Exception{
        logger.log(LogLevelEnum.INFO, "Test message", 4);
        boolean actual = false;
        boolean expected = outContent.toString().contains("INFO");
        assertEquals(actual, expected);
    }

    @Test
    public void log(){
        System.out.print("hello");
        assertEquals("hello", outContent.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}