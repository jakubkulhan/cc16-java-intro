package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Martin on 07.10.2016.
 */
public class PrintStreamLogger implements LoggerInterface, Closeable {

    private PrintStream printStream;

    public PrintStreamLogger(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void log(LogLevelEnum level, String message, int minLogLevel) {
        printStream.printf("[%s]: %s\n", level.name(), message);
        System.out.println("Zalogovano.");
    }

    @Override
    public void close() throws IOException {
        if (printStream != null) {
            printStream.flush();
            printStream.close();
        }
    }

    public PrintStream getPrintStream() {
        return printStream;
    }
    
}
