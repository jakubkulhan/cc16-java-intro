package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.PrintStream;

public class PrintStreamLogger implements LoggerInterface {

    private final PrintStream printstream;

    public PrintStreamLogger(PrintStream printstream) {
        if (printstream == null) {
            throw new IllegalArgumentException();
        }
        this.printstream = printstream;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        printstream.printf("[%s]: %s\n", level.name(), message);
    }

}
