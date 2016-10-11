package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.IOException;

public class StdoutLogger extends BaseLogger implements LoggerInterface {

    @Override
    protected void logFormatted( LogLevelEnum level, String originalMessage, String formattedMessage ) {
        System.out.println( formattedMessage );
    }

    @Override
    public void close() throws IOException {
        // do nothing
    }
}
