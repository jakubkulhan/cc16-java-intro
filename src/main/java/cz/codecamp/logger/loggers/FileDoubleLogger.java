package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.*;

/**
 * Created by Lenovo on 18.10.2016.
 */
public class FileDoubleLogger implements LoggerInterface, Closeable {
    private StringFormatter stringFormatterA, stringFormatterB;
    private PrintStream fileStreamA;
    private PrintStream fileStreamB;

    public FileDoubleLogger() {
        stringFormatterA = new StringFormatter(true);
        stringFormatterB = new StringFormatter(false);
        try {
            fileStreamA = new PrintStream(new FileOutputStream(new File("a.log"), true));
            fileStreamB = new PrintStream(new FileOutputStream(new File("b.log"), true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(LogLevelEnum level, String message) {
            fileStreamA.printf("%s\n", stringFormatterA.format(level, message));
            fileStreamB.printf("%s\n", stringFormatterB.format(level, message));
    }

    @Override
    public void close() throws IOException {
        if(fileStreamA != null) {
            fileStreamA.flush();
            fileStreamA.close();
        }
        if(fileStreamB != null) {
            fileStreamB.flush();
            fileStreamB.close();
        }
    }

}
