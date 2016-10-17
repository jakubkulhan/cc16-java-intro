package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
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
public class MultiLoggerTest {

    PrintStreamLogger printStreamLogger1;
    PrintStreamLogger printStreamLogger2;
    MultiLogger multiLogger;
    DateFormatter formatter = new DateFormatter();
    String[] message = {"Test Message 1", "TestMessage 2"};
    LogLevelEnum[] levelEnum = {LogLevelEnum.INFO, LogLevelEnum.WARNING};

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ByteArrayOutputStream expectedContentStream = new ByteArrayOutputStream();

    PrintStream expectedPrintStream;


    @Before
    public void setUp() throws Exception {

        PrintStream printStream1 = new PrintStream(outputStream);
        printStreamLogger1 = new PrintStreamLogger(printStream1, formatter);
        printStreamLogger2 = new PrintStreamLogger(printStream1, formatter);

        expectedPrintStream = new PrintStream(expectedContentStream);

        LoggerInterface[] logArray = {printStreamLogger1, printStreamLogger2};
        multiLogger = new MultiLogger(logArray);
    }

    @Test
    public void log() throws Exception {

        for (int i = 0; i < message.length; i++) {
            multiLogger.log(levelEnum[i], message[i]);
            expectedPrintStream.println(formatter.format(levelEnum[i], message[i]));
            expectedPrintStream.println(formatter.format(levelEnum[i], message[i]));
        }


        assertEquals(expectedContentStream.toString(), outputStream.toString());


    }



    @After
    public void tearDown() throws Exception {



    }
}