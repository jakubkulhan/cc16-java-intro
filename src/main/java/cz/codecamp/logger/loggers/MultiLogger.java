package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by micha on 05.10.2016.
 */
public class MultiLogger extends BaseLogger implements LoggerInterface {

    private final Collection<LoggerInterface> loggers;

    public MultiLogger( Collection<LoggerInterface> loggers ) {
        this.loggers = loggers;
    }

    @Override
    protected void logFormatted( LogLevelEnum level, String originalMessage, String formattedMessage ) {
        loggers.forEach( x -> x.log( level, originalMessage ) );
    }

    @Override
    public void close() throws IOException {
        for ( LoggerInterface logger : loggers ) {
            logger.close();
        }
    }

    @Override
    public void setFormatter( FormatterInterface formatter ) {
        loggers.forEach( x -> x.setFormatter( formatter ) );
        super.setFormatter( formatter );
    }

    @Override
    public void setMinLogLevel( LogLevelEnum minLogLevel ) {
        loggers.forEach( x -> x.setMinLogLevel( minLogLevel ) );
        super.setMinLogLevel( minLogLevel );
    }
}
