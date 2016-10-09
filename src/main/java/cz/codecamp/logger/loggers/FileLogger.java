package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileLogger implements LoggerInterface {


    private static final String DEFAULT_LOG_FILE = "application.log";
    private final PrintStream printStream;

    public FileLogger() throws FileNotFoundException {
        printStream = new PrintStream(
                new FileOutputStream(DEFAULT_LOG_FILE, true)
        );
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        System.out.printf("[%s]: %s\n", level.name(), message);
    }
}
