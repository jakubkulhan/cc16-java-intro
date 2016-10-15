package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martin on 14.10.2016.
 */
public class FileLoggerTest {

    private FileLogger logger;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        this.logger = new FileLogger();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void navratNazvuLogSouboru() throws Exception {
        String expected = "log " + LocalDateTime.now().format(logger.getFormatterLog()) + ".log";
        String actual = logger.generateLogFileName();
        assertEquals(actual, expected);
    }

    @Test
    public void testNavratoveHodnotyMetodyLog() throws Exception {
        boolean expected = true;
        boolean actual = logger.printLog(LogLevelEnum.INFO, "Test message");
        assertEquals(actual, expected);
    }

    @Test
    public void logMinLogLevel_1_INFO_zalogovano() throws Exception{
        logger.log(LogLevelEnum.INFO, "Test message", 1);
        boolean actual = outContent.toString().contains("Zalogovano do souboru");
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void logMinLogLevel_2_INFO_zalogovano() throws Exception{
        logger.log(LogLevelEnum.INFO, "Test message", 2);
        boolean actual = outContent.toString().contains("Zalogovano do souboru");
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void logMinLogLevel_3_INFO_nezalogovano() throws Exception{
        logger.log(LogLevelEnum.INFO, "Test message", 3);
        boolean actual = outContent.toString().contains("Zalogovano do souboru");
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void logMinLogLevel_4_INFO_nezalogovano() throws Exception{
        logger.log(LogLevelEnum.INFO, "Test message", 4);
        boolean actual = outContent.toString().contains("Zalogovano do souboru");
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void testCloseMetody_FilestreamClosedAleNeniNull() throws Exception{
        logger.close();
        boolean actual = logger.getFileStream() == null ? true : false;
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void testGenerovniNovehoFilu() throws Exception{
        logger.setLogFileName("abc"); // nastaveni na spatnou hodnotu, aby se generoval novy file
        logger.log(LogLevelEnum.INFO, "Test message", 2);
        boolean actual = outContent.toString().contains("Vygenerovan novy log file.");
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

}