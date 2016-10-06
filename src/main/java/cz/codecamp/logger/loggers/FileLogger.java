package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Lenovo on 03.10.2016.
 */
public class FileLogger implements LoggerInterface{

    PrintStream fileStream;

    public FileLogger() throws FileNotFoundException {
        fileStream = new PrintStream(new FileOutputStream(new File("application.log"), true));
    }

    @Override
    public void log(LogLevelEnum level, String message) throws FileNotFoundException {
        fileStream.printf("[%s]: %s\n", level.name(), message);
    }
}