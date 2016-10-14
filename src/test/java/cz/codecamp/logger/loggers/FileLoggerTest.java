package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.utils.LocalDateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

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
    public void testEmptyConstructor() throws Exception {
        // how to test properly? - everything instantiated?
        new FileLogger();
    }

    @Test
    public void testFileSupplierConstructor() throws Exception {
        // how to test properly?
        new FileLogger( FileLogger.DEFAULT_FILE_SUPPLIER );
    }

    @Test
    public void testStreamSupplierConstructor() throws Exception {
        // how to test properly?
        new FileLogger( FileLogger.DEFAULT_FILE_OUTPUTSTREAM_SUPPLIER );
    }

    @Test
    public void testAllArgsConstructor() throws Exception {
        // how to test properly?
        new FileLogger( FileLogger.DEFAULT_FILE_SUPPLIER, FileLogger.DEFAULT_FILE_OUTPUTSTREAM_SUPPLIER );
    }

    @Test
    public void defaultFileSupplier() throws Exception {
        Function<Long, File> fileSupplier = FileLogger.DEFAULT_FILE_SUPPLIER;
        File file = fileSupplier.apply( millis );
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

    @Test(expected = IllegalStateException.class)
    public void logFormattedWrongFile() throws Exception {
        FileLogger logger = new FileLogger( new FileLogger.OutputStreamSupplier(){
            @Override
            public OutputStream get( File file ) throws IOException {
                throw new IOException(  );
            }
        } );
        logger.logFormatted( LogLevelEnum.INFO, originalMessage, formattedMessage );
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