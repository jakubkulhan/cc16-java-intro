package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.formatters.DateFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by lenka.salacova on 10/16/2016.
 */
public class StdoutLoggerTest {

    String testMessage = "TestMessage";
    String testDebugMessage = "TestDebugMessage";
    String testInfoMessage = "TestInfoMessage";
    String testWarningMessage = "TestWarningMessage";
    String testErrorMessage = "TestErrorMessage";

    LogLevelEnum levelEnum = LogLevelEnum.WARNING;

    DateFormatter dateFormatter = new DateFormatter();
    PrintStream defaultPrintStream;
    PrintStream expectedPrintStream;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ByteArrayOutputStream expectedContentStream = new ByteArrayOutputStream();
    StdoutLogger logger;

    @Before
    public void setUp() throws Exception {
        defaultPrintStream = System.out;
        System.setOut(new PrintStream(outputStream));
        expectedPrintStream = new PrintStream(expectedContentStream);
        logger = new StdoutLogger(dateFormatter);

    }

    @Test
    public void log() throws Exception {

        logger.log(levelEnum, testMessage);
        logger.debug(testDebugMessage);
        logger.info(testInfoMessage);
        logger.warning(testWarningMessage);
        logger.error(testErrorMessage);

        expectedPrintStream.println(dateFormatter.format(levelEnum, testMessage));
        expectedPrintStream.println(dateFormatter.format(LogLevelEnum.DEBUG, testDebugMessage));
        expectedPrintStream.println(dateFormatter.format(LogLevelEnum.INFO, testInfoMessage));
        expectedPrintStream.println(dateFormatter.format(LogLevelEnum.WARNING, testWarningMessage));
        expectedPrintStream.println(dateFormatter.format(LogLevelEnum.ERROR, testErrorMessage));

        assertEquals(expectedContentStream.toString(), outputStream.toString());
    }

    @After
    public void tearDown() throws Exception {

        System.setOut(defaultPrintStream);

    }

}