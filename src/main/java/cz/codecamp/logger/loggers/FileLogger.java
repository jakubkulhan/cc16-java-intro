package cz.codecamp.logger.loggers;

import com.sun.istack.internal.logging.Logger;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

/**
 * Created by micha on 05.10.2016.
 */
public class FileLogger extends BaseLogger implements LoggerInterface {
    private static final String FORMAT_DATE = "yyyy-MM-dd";
    private LocalDateTime lastDateTime = null;
    private Writer writer = null;

    public FileLogger() {
    }

    @Override
    protected void logFormatted( LogLevelEnum level, String originalMessage, String formattedMessage ) {
        try {
            getFileWriter().append( formattedMessage ).append( System.lineSeparator() );
        } catch ( IOException e ) {
            Logger.getLogger( FileLogger.class ).log( Level.SEVERE, "Could not log into file: " + getFile().getAbsolutePath(), e );
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
        LocalDateTime now = LocalDateTime.now();
        if ( writer == null || lastDateTime == null || ( now.getDayOfYear() != lastDateTime.getDayOfYear() && now.getMonth() != lastDateTime.getMonth() && now.getYear() != now.getYear() ) ) {
            lastDateTime = now;
            writer = new BufferedWriter( new FileWriter( getFile(), true ) );
        }
        return writer;
    }

    private File getFile() {
        return new File( "application_" + lastDateTime.format( DateTimeFormatter.ofPattern( FORMAT_DATE ) ) + ".log" );
    }
}
