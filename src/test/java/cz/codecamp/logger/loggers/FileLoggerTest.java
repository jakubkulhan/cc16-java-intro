package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.Assert.*;


/**
 * Created by jbares on 14.10.2016.
 */
public class FileLoggerTest implements LoggerTestInterface{

    private FileLogger logger;
    private final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();


    @Before
    public void init() throws Exception {
        this.logger = new FileLogger();
        System.setOut(new PrintStream(byteStream));
    }

    @Test
    public void testLogCreation() throws Exception {
        assertTrue(logger.createLog(LogLevelEnum.ERROR, "Real Slim Shady is causing havoc"));
    }

    @Test
    public void testLevels() throws Exception{
        logger.log(LogLevelEnum.ERROR, "Real Slim Shady is causing havoc", 4);
        assertFalse(byteStream.toString().contains("Slim Shady"));
    }
    @Test
    public void testFileName() throws Exception {
        assertEquals(logger.createLogFileName(),"log " + format.format(new Date()) + ".log");
    }

    @Test
    public void testCloseNotNull() throws Exception{
        logger.close();
        assertNotNull(logger.getFileStream());
    }

    @Test
    public void testNewFile() throws Exception{
        logger.setLogFileName("testFile.log");
        logger.log(LogLevelEnum.WARNING, "Attention, please. May the real Slim Shady please stand up?", 2);
        assertTrue(byteStream.toString().contains("Created new file."));
    }

    @After
    public void end() {
        System.setOut(null);
    }

}