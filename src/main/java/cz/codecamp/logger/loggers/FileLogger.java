package cz.codecamp.logger.loggers;

import com.sun.istack.internal.logging.Logger;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.utils.LocalDateTimeUtils;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

/**
 * Created by micha on 05.10.2016.
 */
public class FileLogger extends BaseLogger implements LoggerInterface {
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static FileSupplier DEFAULT_FILE_SUPPLIER = ( time ) -> new File( "application_" + LocalDateTimeUtils.fromMillis( time ).format( DateTimeFormatter.ofPattern( FORMAT_DATE ) ) + ".log" );
    public static OutputStreamSupplier DEFAULT_FILE_OUTPUTSTREAM_SUPPLIER = file -> new FileOutputStream( file, true );
    private LocalDateTime lastDateTime = LocalDateTime.ofInstant( Instant.ofEpochMilli( 0L ), ZoneId.systemDefault() );
    private Writer writer = null;
    private FileSupplier fileSupplier;
    private OutputStreamSupplier outputStreamSupplier;

    public FileLogger() {
        this.fileSupplier = DEFAULT_FILE_SUPPLIER;
        this.outputStreamSupplier = DEFAULT_FILE_OUTPUTSTREAM_SUPPLIER;
    }

    public FileLogger( FileSupplier fileSupplier ) {
        this.fileSupplier = fileSupplier;
        this.outputStreamSupplier = DEFAULT_FILE_OUTPUTSTREAM_SUPPLIER;
    }

    public FileLogger( OutputStreamSupplier outputStreamSupplier ) {
        this.fileSupplier = DEFAULT_FILE_SUPPLIER;
        this.outputStreamSupplier = outputStreamSupplier;
    }

    public FileLogger( FileSupplier fileSupplier, OutputStreamSupplier outputStreamSupplier ) {
        this.fileSupplier = fileSupplier;
        this.outputStreamSupplier = outputStreamSupplier;
    }

    @Override
    protected void logFormatted( LogLevelEnum level, String originalMessage, String formattedMessage ) {
        try {
            getFileWriter().append( formattedMessage ).append( System.lineSeparator() ).flush();
        } catch ( IOException e ) {
            // log error inside logger
        }

    }

    @Override
    public void close() throws IOException {
        if ( writer != null ) {
            writer.close();
            writer = null;
        }
    }

    private Writer getFileWriter() throws IOException {
        LocalDateTime now = LocalDateTimeUtils.fromMillis( getTimeSupplier().get() );
        if ( writer == null || lastDateTime == null || ( now.getDayOfYear() != lastDateTime.getDayOfYear() && now.getMonth() != lastDateTime.getMonth() && now.getYear() != now.getYear() ) ) {
            lastDateTime = now;
            writer = new OutputStreamWriter( outputStreamSupplier.get( fileSupplier.get( getTimeSupplier().get() ) ) );
        }
        return writer;
    }

    public interface FileSupplier {
        File get( long timeInMillis );
    }

    public interface OutputStreamSupplier {
        OutputStream get( File file ) throws IOException;
    }
}
