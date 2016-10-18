package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;

import static org.junit.Assert.*;
/**
 * Created by jbares on 14.10.2016.
 */
public class PrintStreamLoggerTest {

    private PrintStreamLogger logger;
    private final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

    @Before
    public void init() throws Exception {
        PrintStream stream = new PrintStream(new File("test.log"));
        this.logger = new PrintStreamLogger(stream);
        System.setOut(new PrintStream(byteStream));
    }

    @Test
    public void testLog(){
        logger.log(LogLevelEnum.WARNING, "Attention, please. May the real Slim Shady please stand up?", 1);
        assertTrue(byteStream.toString().contains("Slim Shady"));
    }

    @Test
    public void testLogLevel(){
        logger.log(LogLevelEnum.INFO, "The real Slim Shady won't stand up.", 3);
        assertFalse(byteStream.toString().contains("Slim Shady"));
    }

    @Test
    public void testClose() throws Exception{
        logger.close();
        assertTrue(logger.getPrintStream() == null);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

}
