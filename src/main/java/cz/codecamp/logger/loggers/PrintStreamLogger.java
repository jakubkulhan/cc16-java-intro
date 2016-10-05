package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by filip on 03/10/16.
 */
public class PrintStreamLogger implements LoggerInterface {
    private PrintStream printStream;

    PrintStreamLogger(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        printStream.println(level.toString() + message);
    }
}
