package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by blaha on 13.10.2016.
 */
public class BaseLoggerTest {
    LogLevelEnum level;
    String originalMessage;
    String formattedMessage;
    BaseLogger logger;
    FormatterInterface formatter;
    long millis;
    BaseLogger stubLogger = new BaseLogger() {
        @Override
        protected void logFormatted( LogLevelEnum level, String originalMessage, String formattedMessage ) {
            // do nothing
        }

        @Override
        public void close() throws IOException {
            // do nothing
        }
    };


    @Before
    public void setUp() throws Exception {
        millis = 1000000;
        level = LogLevelEnum.ERROR;
        originalMessage = "originalMessage";
        formattedMessage = "formatted message";
        formatter = mock( FormatterInterface.class );
        when( formatter.format( any(), eq( originalMessage ), anyLong(), anyString(), anyInt() ) ).thenReturn( formattedMessage );
        logger = mock( BaseLogger.class, Mockito.CALLS_REAL_METHODS );
        when( logger.getFormatter() ).thenReturn( formatter );
        when( logger.getMinLogLevel() ).thenReturn( LogLevelEnum.DEBUG );
        when( logger.getTimeSupplier() ).thenReturn( () -> millis );
        when( logger.getStackTraceElementSupplier() ).thenReturn( ( elements ) -> elements[0] );
    }

    @Test
    public void log() throws Exception {
        logger.log( level, originalMessage );
        verify( logger ).logFormatted( level, originalMessage, formattedMessage );
    }

    @Test
    public void logLowLevel() throws Exception {
        when( logger.getMinLogLevel() ).thenReturn( LogLevelEnum.ERROR );
        logger.log( LogLevelEnum.WARNING, originalMessage );
        verify( logger, never() ).logFormatted( any(), anyString(), anyString() );
    }

    @Test
    public void defaultStackTraceSupplier() throws Exception {
        Function<StackTraceElement[], StackTraceElement> defaultStacktraceElementSupplier = BaseLogger.DEFAULT_STACKTRACE_ELEMENT_SUPPLIER;
        StackTraceElement[] stackTrace = new StackTraceElement[]{
                new StackTraceElement( BaseLogger.class.getName(), "log", "file", 1 ),
                new StackTraceElement( "SomeClass", "log", "file", 1 ) // also tests default package - no package
        };
        assertEquals( stackTrace[1], defaultStacktraceElementSupplier.apply( stackTrace ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public void defaultStackTraceSupplierException() throws Exception {
        Function<StackTraceElement[], StackTraceElement> defaultStacktraceElementSupplier = BaseLogger.DEFAULT_STACKTRACE_ELEMENT_SUPPLIER;
        StackTraceElement[] stackTrace = new StackTraceElement[]{
                new StackTraceElement( BaseLogger.class.getName(), "log", "file", 1 ),
                new StackTraceElement( BaseLogger.class.getName(), "log", "file", 1 )
        };
        defaultStacktraceElementSupplier.apply( stackTrace );
    }

    @Test
    public void setFormatter() throws Exception {

    }

    @Test
    public void setMinLogLevel() throws Exception {

    }

    @Test
    public void setTimeSupplier() throws Exception {
        Supplier<Long> timeSupplier1 = () -> 1L;
        Supplier<Long> timeSupplier2 = () -> 2L;
        stubLogger.setTimeSupplier( timeSupplier1 );
        assertEquals( timeSupplier1, stubLogger.getTimeSupplier() );
        stubLogger.setTimeSupplier( timeSupplier2 );
        assertEquals( timeSupplier2, stubLogger.getTimeSupplier() );
    }

    @Test
    public void setStackTraceElementSupplier() throws Exception {
        Function<StackTraceElement[], StackTraceElement> supplier1 = mock( Function.class );
        Function<StackTraceElement[], StackTraceElement> supplier2 = mock( Function.class );
        stubLogger.setStackTraceElementSupplier( supplier1 );
        assertEquals( supplier1, stubLogger.getStackTraceElementSupplier() );
        stubLogger.setStackTraceElementSupplier( supplier2 );
        assertEquals( supplier2, stubLogger.getStackTraceElementSupplier() );
    }
}