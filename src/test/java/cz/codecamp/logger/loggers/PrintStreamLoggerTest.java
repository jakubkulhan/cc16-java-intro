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
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by blaha on 12.10.2016.
 */
public class PrintStreamLoggerTest {
    LogLevelEnum level;
    String originalMessage;
    String formattedMessage;

    @Before
    public void setUp() throws Exception {
        level = LogLevelEnum.ERROR;
        originalMessage = "originalMessage";
        formattedMessage = "formatted message";
    }

    @Test
    public void logFormatted() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStreamLogger logger = new PrintStreamLogger( new PrintStream( outContent ) );
        logger.logFormatted( level, originalMessage, formattedMessage );
        assertEquals( formattedMessage + System.lineSeparator(), outContent.toString() );
    }

    @Test
    public void close() throws Exception {
        PrintStream stream = mock( PrintStream.class );
        PrintStreamLogger logger = new PrintStreamLogger( stream );
        logger.close();
        verify( stream, times( 1 ) ).close();
    }

}