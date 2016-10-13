package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
    }

    @Test
    public void log() throws Exception {
        logger.log( level, originalMessage );
        verify( logger ).logFormatted( level, originalMessage, formattedMessage );
    }

    @Test
    public void logLowLevel() throws Exception {
        when( formatter.format( any(), eq( originalMessage ), anyLong(), anyString(), anyInt() ) ).thenReturn( formattedMessage );
        // create complete stub...
        logger.setMinLogLevel( LogLevelEnum.ERROR );
        logger.log( LogLevelEnum.WARNING, originalMessage );
        verify( logger, never() ).logFormatted( any(), anyString(), anyString() );
    }

    @Test
    public void setFormatter() throws Exception {

    }

    @Test
    public void setMinLogLevel() throws Exception {

    }

    @Test
    public void setTimeSupplier() throws Exception {

    }

    @Test
    public void getFormatter() throws Exception {

    }

    @Test
    public void getMinLogLevel() throws Exception {

    }

    @Test
    public void getTimeSupplier() throws Exception {

    }

}