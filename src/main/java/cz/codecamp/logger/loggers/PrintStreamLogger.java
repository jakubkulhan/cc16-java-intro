package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.PrintStream;

/**
 * Created by lenka.salacova on 10/5/2016.
 */
public class PrintStreamLogger implements LoggerInterface {

    private PrintStream printStream;

    public PrintStreamLogger(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        this.printStream.printf("[%s]: %s\n", level.name(), message);

    }
}
