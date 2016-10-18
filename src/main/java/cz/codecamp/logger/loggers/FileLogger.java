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
public class FileLogger extends AbstractLogger {
    @Override
    public void log(LogLevelEnum level, String message) {
        try {
            PrintStream fileStream = new PrintStream(new FileOutputStream(new File("application.log"), true));
            fileStream.println(level.toString() + message);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String msg) {
        try {
            PrintStream fileStream = new PrintStream(new FileOutputStream(new File("application.log"), true));
            fileStream.println(msg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
