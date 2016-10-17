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
public class PrintSteamLoggerLevelComparisonTest {


    DateFormatter formatter = new DateFormatter();
    LogLevelEnum minLevelEnum = LogLevelEnum.WARNING;
    String[] message = {"Test Message 1", "TestMessage 2"};
    LogLevelEnum[] levelEnum = {LogLevelEnum.INFO, LogLevelEnum.WARNING};

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ByteArrayOutputStream expectedContentStream = new ByteArrayOutputStream();
    PrintStream expectedPrintStream;
    PrintSteamLoggerLevelComparison logger;

    @Before
    public void setUp() throws Exception {

        expectedPrintStream = new PrintStream(expectedContentStream);
        logger = new PrintSteamLoggerLevelComparison(new PrintStream(outputStream), formatter, minLevelEnum);

    }

    @Test
    public void log() throws Exception {

        for (int i = 0; i < message.length; i++) {
            logger.log(levelEnum[i], message[i]);

        }
        expectedPrintStream.println(formatter.format(levelEnum[1], message[1]));

        assertEquals(expectedContentStream.toString(), outputStream.toString());

    }

    @After
    public void tearDown() throws Exception {

    }
}