package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.formatters.JsonFormatter;
import cz.codecamp.logger.formatters.SimpleFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by blaha on 06.10.2016.
 */
public class StdoutLoggerTest {

    LogLevelEnum level;
    String originalMessage;
    String formattedMessage;
    StdoutLogger logger;
    ByteArrayOutputStream outContent;

    @Before
    public void setUp() throws Exception {
        level = LogLevelEnum.ERROR;
        originalMessage = "originalMessage";
        formattedMessage = "formatted message";
        outContent = new ByteArrayOutputStream();
        System.setOut( new PrintStream( outContent ) );
        logger = new StdoutLogger();
    }

    @Test
    public void logFormatted() throws Exception {
        logger.logFormatted( level, originalMessage, formattedMessage );
        assertEquals( formattedMessage + System.lineSeparator(), outContent.toString() );
    }

    @Test
    public void close() throws Exception {
        // I don't expect this method to do anything
        logger.close();
    }

    @After
    public void tearDown() throws Exception {
        System.setOut( null );
    }
}