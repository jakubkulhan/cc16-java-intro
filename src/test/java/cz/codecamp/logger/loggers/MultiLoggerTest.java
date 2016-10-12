package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by blaha on 12.10.2016.
 */
@RunWith( Parameterized.class )
public class MultiLoggerTest {
    private static final String TEXT_MESSAGE = "Test message";

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return IntStream.range( 0, 3 ).boxed()
                .map( numberOfLoggers -> new Object[]{ TEXT_MESSAGE, numberOfLoggers } )
                .collect( Collectors.toList() );
    }

    final LogLevelEnum level;
    final String message;
    final FormatterInterface formatter;
    final int numberOfLoggers;
    String formatted;
    List<PrintStreamLogger> loggers;
    List<ByteArrayOutputStream> outContents;
    List<MyPrintStream> printStreams;
    MultiLogger logger;

    public MultiLoggerTest( String message, int numberOfLoggers ) {
        this.level = LogLevelEnum.ERROR;
        this.message = message;
        this.formatter = new SimpleFormatter();
        this.numberOfLoggers = numberOfLoggers;
    }

    @Before
    public void setUp() throws Exception {
        loggers = new ArrayList<>();
        outContents = new ArrayList<>();
        printStreams = new ArrayList<>();
        for ( int i = 0; i < numberOfLoggers; i++ ) {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            MyPrintStream printStream = new MyPrintStream( outContent );
            PrintStreamLogger printStreamLogger = new PrintStreamLogger( printStream );
            printStreamLogger.setFormatter( formatter );
            loggers.add( printStreamLogger );
            outContents.add( outContent );
            printStreams.add( printStream );
        }
        logger = new MultiLogger( loggers );
    }

    @Test
    public void logFormatted() throws Exception {
        // multilogger calls BaseLogger.log, which automatically adds caller's class and line, which cannot be determined
        Pattern pattern = Pattern.compile( String.format( "\\[%s\\] \\[[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{1,2}:[0-9]{2}:[0-9]{2}\\] \\[.+:[0-9]+\\] %s" + System.lineSeparator(), level.name(), message ) );
        StackTraceElement traceElement = getCallingStackTraceElement();
        formatted = formatter.format( level, message, traceElement.getClassName(), traceElement.getLineNumber() );
        logger.logFormatted( level, message, formatted );
        outContents.forEach( outContent -> assertTrue( pattern.matcher( outContent.toString() ).matches() ) );
        outContents.forEach( outContent -> assertEquals( formatted + System.lineSeparator(), outContent.toString() ) );


    }

    @Test
    public void close() throws Exception {
        printStreams.forEach( printStream -> assertFalse( printStream.isClosed() ) );
        logger.close();
        printStreams.forEach( printStream -> assertTrue( printStream.isClosed() ) );
    }

    @Test
    public void setFormatter() throws Exception {
        FormatterInterface newFormatter = new SimpleFormatter();
        loggers.forEach( l -> assertNotEquals( newFormatter, l.getFormatter() ) );
        logger.setFormatter( newFormatter );
        loggers.forEach( l -> assertEquals( newFormatter, l.getFormatter() ) );
    }

    @Test
    public void setMinLogLevel() throws Exception {
        LogLevelEnum newLevel = level.equals( LogLevelEnum.WARNING ) ? LogLevelEnum.DEBUG : LogLevelEnum.WARNING;
        loggers.forEach( l -> assertNotEquals( newLevel, l.getMinLogLevel() ) );
        logger.setMinLogLevel( newLevel );
        loggers.forEach( l -> assertEquals( newLevel, l.getMinLogLevel() ) );
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

    private StackTraceElement getCallingStackTraceElement() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i = 0;
        while ( stackTrace[i].getClass().getPackage().equals( BaseLogger.class.getPackage() ) ) {
            i++;
        }
        if ( i < stackTrace.length ) {
            return stackTrace[i];
        }
        throw new IllegalArgumentException( "Could not find caller in the StackTrace." );
    }
}