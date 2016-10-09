package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Lenovo on 5.10.16.
 */
public class PrintStreamLogger implements LoggerInterface, PragmaticLoggerInterface, Closeable {
private PrintStream printStream;

public PrintStreamLogger(PrintStream printStream) {
    this.printStream = printStream;
    }


    @Override
    public void log(LogLevelEnum level, String message) {
        printStream.printf("[%s]: %s\n", level.name(), message);
    }

    @Override
    public void close() throws IOException {
        if(printStream != null) {
            printStream.flush();
            printStream.close();
        }
    }

    @Override
    public void debug(String message) {
        printStream.printf("[%s]: %s\n", LogLevelEnum.DEBUG, message);
    }

    @Override
    public void info(String message) {
        printStream.printf("[%s]: %s\n", LogLevelEnum.INFO, message);
    }

    @Override
    public void warning(String message) {
        printStream.printf("[%s]: %s\n", LogLevelEnum.WARNING, message);
    }

    @Override
    public void error(String message) {
        printStream.printf("[%s]: %s\n", LogLevelEnum.ERROR, message);
    }
}