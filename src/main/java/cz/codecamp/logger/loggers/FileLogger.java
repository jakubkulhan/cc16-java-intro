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
    private static final String FORMAT_DATE = "yyyy-MM-dd";
    private LocalDateTime lastDateTime = LocalDateTime.ofInstant( Instant.ofEpochMilli( 0L ), ZoneId.systemDefault() );
    private Writer writer = null;
    private FileSupplier fileSupplier;

    public FileLogger() {
        this.fileSupplier = ( time ) -> new File( "application_" + LocalDateTimeUtils.fromMillis( time ).format( DateTimeFormatter.ofPattern( FORMAT_DATE ) ) + ".log" );
    }

    public FileLogger( FileSupplier fileSupplier ) {
        this.fileSupplier = fileSupplier;
    }

    @Override
    protected void logFormatted( LogLevelEnum level, String originalMessage, String formattedMessage ) {
        try {
            getFileWriter().append( formattedMessage ).append( System.lineSeparator() );
        } catch ( IOException e ) {
            Logger.getLogger( FileLogger.class ).log( Level.SEVERE, "Could not log into file.", e );
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
            writer = new BufferedWriter( new FileWriter( fileSupplier.get( getTimeSupplier().get() ), true ) );
        }
        return writer;
    }

    public interface FileSupplier {
        File get( long timeInMillis );
    }
}
