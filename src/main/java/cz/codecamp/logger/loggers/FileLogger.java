package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Bulgn Mandzhieva
 */
public class FileLogger implements LoggerInterface {

    @Override
    public void log(LogLevelEnum level, String message) {
        try (PrintStream fileStream = new PrintStream(new FileOutputStream(new File("application.log"), true))) {
            fileStream.print(level.name() + ": ");
            fileStream.println(message);
        } catch (Exception e) {
            System.out.println("error occured: " + e.getMessage());
        }
    }
}
