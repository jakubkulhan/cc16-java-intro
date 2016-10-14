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
import static org.mockito.Mockito.*;

/**
 * Created by blaha on 12.10.2016.
 */
@RunWith( Parameterized.class )
public class MultiLoggerTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return IntStream.range( 0, 3 ).boxed()
                .map( numberOfLoggers -> new Object[]{ numberOfLoggers } )
                .collect( Collectors.toList() );
    }

    final int numberOfLoggers;
    LogLevelEnum level;
    String originalMessage;
    String formattedMessage;
    List<LoggerInterface> loggers;
    MultiLogger logger;

    public MultiLoggerTest( int numberOfLoggers ) {
        this.numberOfLoggers = numberOfLoggers;
    }

    @Before
    public void setUp() throws Exception {
        level = LogLevelEnum.ERROR;
        originalMessage = "originalMessage";
        formattedMessage = "formatted message";
        loggers = new ArrayList<>();
        for ( int i = 0; i < numberOfLoggers; i++ ) {
            loggers.add( mock( LoggerInterface.class ) );
        }
        logger = new MultiLogger( loggers );
    }

    @Test
    public void logFormatted() throws Exception {
        logger.logFormatted( level, originalMessage, formattedMessage );
        for ( LoggerInterface l : loggers ) {
            verify( l ).log( level, originalMessage );
        }
    }

    @Test
    public void close() throws Exception {
        logger.close();
        for ( LoggerInterface l : loggers ) {
            verify( l ).close();
        }
    }

    @Test
    public void setFormatter() throws Exception {
        FormatterInterface formatter = mock( FormatterInterface.class );
        logger.setFormatter( formatter );
        loggers.forEach( l -> verify( l ).setFormatter( formatter ) );
    }

    @Test
    public void setMinLogLevel() throws Exception {
        logger.setMinLogLevel( level );
        loggers.forEach( l -> verify( l ).setMinLogLevel( level ) );
    }
}