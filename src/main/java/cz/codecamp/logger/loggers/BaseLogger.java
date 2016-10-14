package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.formatters.SimpleFormatter;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by micha on 05.10.2016.
 */
public abstract class BaseLogger implements LoggerInterface {
    public static Function<StackTraceElement[], StackTraceElement> DEFAULT_STACKTRACE_ELEMENT_SUPPLIER = defaultStackTraceSupplier();
    private FormatterInterface formatter;
    private LogLevelEnum minLogLevel;
    private Supplier<Long> timeSupplier;
    private Function<StackTraceElement[], StackTraceElement> stackTraceElementSupplier;

    public BaseLogger() {
        this.formatter = new SimpleFormatter();
        this.minLogLevel = LogLevelEnum.DEBUG;
        this.timeSupplier = () -> System.currentTimeMillis();
        this.stackTraceElementSupplier = defaultStackTraceSupplier();
    }

    @Override
    public void log( LogLevelEnum level, String message ) {
        StackTraceElement stackTraceElement = getStackTraceElementSupplier().apply( Thread.currentThread().getStackTrace() );
        if ( getMinLogLevel().isLowerOrEqualTo( level ) ) { // task #4
            logFormatted( level, message, getFormatter().format( level, message, getTimeSupplier().get(), stackTraceElement.getClassName(), stackTraceElement.getLineNumber() ) ); // task #5
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

    @Override
    public void setStackTraceElementSupplier( Function<StackTraceElement[], StackTraceElement> traceElementSupplier ) {
        this.stackTraceElementSupplier = traceElementSupplier;
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

    protected Function<StackTraceElement[], StackTraceElement> getStackTraceElementSupplier() {
        return stackTraceElementSupplier;
    }

    protected abstract void logFormatted( LogLevelEnum level, String originalMessage, String formattedMessage );

    private static Function<StackTraceElement[], StackTraceElement> defaultStackTraceSupplier() {
        return ( stackTrace ) -> {
            String loggerPackageName = BaseLogger.class.getPackage().getName();
            int i = 0;
            while ( i < stackTrace.length ) {
                String className = stackTrace[i].getClassName();
                int pos = className.lastIndexOf( '.' );
                pos = pos >= 0 ? pos : 0;
                String packageName = className.substring( 0, pos );
                if ( packageName.equals( loggerPackageName ) ) {
                    i++;
                } else {
                    break;
                }
            }
            if ( i < stackTrace.length ) {
                return stackTrace[i];
            }
            throw new IllegalArgumentException( "Could not find caller in the StackTrace." );
        };
    }
}
