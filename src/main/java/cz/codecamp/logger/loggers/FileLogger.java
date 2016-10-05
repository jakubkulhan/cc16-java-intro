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
public class FileLogger implements LoggerInterface {
    @Override
    public void log(LogLevelEnum level, String message) {
        try {
            PrintStream fileStream = new PrintStream(new FileOutputStream(new File("application.log"), true));
            fileStream.println(level.toString() + message);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
