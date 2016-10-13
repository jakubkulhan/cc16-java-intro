package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.utils.LocalDateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by blaha on 11.10.2016.
 */
public class FileLoggerTest {

    LogLevelEnum level;
    String originalMessage;
    String formattedMessage;
    long millis = 1000000;

    @Before
    public void setUp() throws Exception {
        level = LogLevelEnum.ERROR;
        originalMessage = "originalMessage";
        formattedMessage = "formatted message";
    }

    @Test
    public void defaultFileSupplier() throws Exception {
        FileLogger.FileSupplier fileSupplier = FileLogger.DEFAULT_FILE_SUPPLIER;
        File file = fileSupplier.get( millis );
        assertEquals( "application_" + LocalDateTimeUtils.fromMillis( millis ).format( DateTimeFormatter.ofPattern( FileLogger.FORMAT_DATE ) ) + ".log", file.getName() );
    }

    @Test
    public void logFormatted() throws Exception {
        OutputStream stream = new ByteArrayOutputStream();
        FileLogger logger = new FileLogger( new FileLogger.OutputStreamSupplier() {
            @Override
            public OutputStream get( File file ) throws IOException {
                return stream;
            }
        } );
        logger.logFormatted( LogLevelEnum.INFO, originalMessage, formattedMessage );
        assertEquals( formattedMessage + System.lineSeparator(), stream.toString() );
    }

    @Test
    public void close() throws Exception {
        final OutputStream stream = mock( OutputStream.class );
        FileLogger logger = new FileLogger( new FileLogger.OutputStreamSupplier() {
            @Override
            public OutputStream get( File file ) throws IOException {
                return stream;
            }
        } );
        logger.logFormatted( LogLevelEnum.INFO, originalMessage, formattedMessage ); // must be called, otherwise the stream is not even open and therefore does not require closing
        logger.close();
        verify( stream, times( 1 ) ).close();
    }

    @After
    public void tearDown() throws Exception {
    }
}