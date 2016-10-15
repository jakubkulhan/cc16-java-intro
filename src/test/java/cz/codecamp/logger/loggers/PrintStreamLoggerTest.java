package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martin on 15.10.2016.
 */
public class PrintStreamLoggerTest {

    private PrintStreamLogger logger;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        PrintStream printStream = new PrintStream(new File("abc.txt"));
        this.logger = new PrintStreamLogger(printStream);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testMetodyLog(){
        logger.log(LogLevelEnum.INFO, "Test Message", 2);
        boolean actual = outContent.toString().contains("Zalogovano");
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void testClose() throws Exception{
        logger.close();
        boolean actual = logger.getPrintStream() == null ? true : false;
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

}