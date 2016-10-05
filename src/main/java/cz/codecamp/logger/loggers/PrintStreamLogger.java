package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamLogger implements LoggerInterface, PragmaticLoggerInterface {

    protected PrintStream printStream;

    public PrintStreamLogger(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        this.printStream.printf("[%s]: %s\n", level.name(), message);
    }

}
