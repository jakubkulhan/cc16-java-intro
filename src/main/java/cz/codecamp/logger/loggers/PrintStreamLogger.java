package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamLogger implements LoggerInterface, Closeable {

    private PrintStream printStream;

    public PrintStreamLogger(PrintStream printStream){
        this.printStream = printStream;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        printStream.print(level.name() + " " + message);
    }

    @Override
    public void close() throws IOException {
        printStream.close();
    }
}
