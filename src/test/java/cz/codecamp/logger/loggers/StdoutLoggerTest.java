package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


/**
 * Created by jbares on 14.10.2016.
 */
public class StdoutLoggerTest implements LoggerTestInterface {

    private final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

    private StdoutLogger logger;

    @Before
    public void init() throws Exception {
        System.setOut(new PrintStream(byteStream));
        this.logger = new StdoutLogger();
    }

    @Test
    public void testLogCreation() throws Exception {
        assertTrue(logger.createLog(LogLevelEnum.ERROR, "Real Slim Shady is causing havoc"));
    }

    @Test
    public void testLevels() throws Exception{
        logger.log(LogLevelEnum.ERROR, "Real Slim Shady is causing havoc", 4);
        assertFalse(byteStream.toString().contains("INFO"));
    }

    @After
    public void end() {
        System.setOut(null);
    }
}
