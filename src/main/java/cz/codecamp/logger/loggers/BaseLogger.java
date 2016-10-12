package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.formatters.SimpleFormatter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.function.Supplier;

/**
 * Created by micha on 05.10.2016.
 */
public abstract class BaseLogger implements LoggerInterface {
    private FormatterInterface formatter;
    private LogLevelEnum minLogLevel;
    private Supplier<Long> timeSupplier;

    public BaseLogger() {
        this.formatter = new SimpleFormatter();
        this.minLogLevel = LogLevelEnum.DEBUG;
        this.timeSupplier = () -> System.currentTimeMillis();
    }

    @Override
    public void log( LogLevelEnum level, String message ) {
        StackTraceElement stackTraceElement = getCallingStackTraceElement( Thread.currentThread().getStackTrace() );
        if ( minLogLevel.isLowerOrEqualTo( level ) ) { // task #4
            logFormatted( level, message, formatter.format( level, message, timeSupplier.get(), stackTraceElement.getClassName(), stackTraceElement.getLineNumber() ) ); // task #5
        }
    }

    @Override
    public void setFormatter( FormatterInterface formatter ) {
        this.formatter = formatter;
    }

    @Override
    public void setMinLogLevel( LogLevelEnum minLogLevel ) {
        this.minLogLevel = minLogLevel;
    }

    @Override
    public void setTimeSupplier( Supplier<Long> timeSupplier ) {
        this.timeSupplier = timeSupplier;
    }

    protected FormatterInterface getFormatter() {
        return formatter;
    }

    protected LogLevelEnum getMinLogLevel() {
        return minLogLevel;
    }

    protected Supplier<Long> getTimeSupplier() {
        return timeSupplier;
    }

    protected abstract void logFormatted( LogLevelEnum level, String originalMessage, String formattedMessage );

    private StackTraceElement getCallingStackTraceElement( StackTraceElement[] stackTrace ) {
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
