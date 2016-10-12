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

/**
 * Created by blaha on 12.10.2016.
 */
@RunWith( Parameterized.class )
public class PrintStreamLoggerTest {
    private static final String TEXT_MESSAGE = "Test message";
    private static final FormatterInterface[] formatters = new FormatterInterface[]{ new SimpleFormatter(), new JsonFormatter() };

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.stream( LogLevelEnum.values() )
                .flatMap( logLevelEnum -> Arrays.stream( formatters )
                        .map( formatter -> new Object[]{ logLevelEnum, TEXT_MESSAGE, formatter } ) )
                .collect( Collectors.toList() );
    }

    final LogLevelEnum level;
    final String message;
    final FormatterInterface formatter;
    String formatted;
    ByteArrayOutputStream outContent;
    PrintStreamLogger logger;
    MyPrintStream printStream;

    public PrintStreamLoggerTest( LogLevelEnum level, String message, FormatterInterface formatter ) {
        this.level = level;
        this.message = message;
        this.formatter = formatter;
    }

    @Before
    public void setUp() throws Exception {
        formatted = formatter.format( level, message );
        outContent = new ByteArrayOutputStream();
        printStream = new MyPrintStream( outContent );
        logger = new PrintStreamLogger( printStream );
    }

    @Test
    public void logFormatted() throws Exception {
        logger.logFormatted( level, message, formatted );
        assertEquals( formatted + System.lineSeparator(), outContent.toString() );
    }

    @Test
    public void close() throws Exception {
        assertFalse( printStream.isClosed() );
        logger.close();
        assertTrue( printStream.isClosed() );
    }

    private static class MyPrintStream extends PrintStream {
        private boolean closed = false;

        public MyPrintStream( OutputStream out ) {
            super( out );
        }

        @Override
        public void close() {
            super.close();
            closed = true;
        }

        public boolean isClosed() {
            return closed;
        }
    }
}