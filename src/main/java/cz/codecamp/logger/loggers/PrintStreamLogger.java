package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.PrintStream;

/**
 * Created by vkorecky on 4.10.16.
 */
public class PrintStreamLogger implements LoggerInterface {
    private PrintStream stream;

    public PrintStreamLogger(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        stream.printf("[%s]: %s\n", level.name(), message);
    }
}
