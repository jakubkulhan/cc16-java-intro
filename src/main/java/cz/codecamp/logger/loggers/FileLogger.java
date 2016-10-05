package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.*;

/**
 * Created by micha on 05.10.2016.
 */
public class FileLogger extends BaseLogger implements LoggerInterface {
    private final PrintStreamLogger printStreamLogger;

    public FileLogger() {
        try {
            printStreamLogger = new PrintStreamLogger(
                    new PrintStream(new FileOutputStream(new File("application.log"), true)));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("New file not found, wtf.", e);
        }
    }

    @Override
    protected void logFormatted(LogLevelEnum level, String originalMessage, String formattedMessage) {
        printStreamLogger.log(level, formattedMessage);
    }

    @Override
    public void close() throws IOException {
        printStreamLogger.close();
    }

    @Override
    public void setFormatter(FormatterInterface formatter) {
        printStreamLogger.setFormatter(formatter);
        super.setFormatter(formatter);
    }

    @Override
    public void setMinLogLevel(LogLevelEnum minLogLevel) {
        printStreamLogger.setMinLogLevel(minLogLevel);
        super.setMinLogLevel(minLogLevel);
    }
}
