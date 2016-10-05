package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by micha on 05.10.2016.
 */
public class PrintStreamLogger extends BaseLogger implements LoggerInterface {

    private final PrintStream printStream;

    public PrintStreamLogger(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    protected void logFormatted(LogLevelEnum level, String originalMessage, String formattedMessage) {
        printStream.append(formattedMessage);
    }

    @Override
    public void close() throws IOException {
        printStream.close();
    }
}
