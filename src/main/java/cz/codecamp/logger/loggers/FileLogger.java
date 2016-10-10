package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Bulgn Mandzhieva
 */
public class FileLogger implements LoggerInterface {

    private PrintStream fileStream;

    public FileLogger() {
        try {
            this.fileStream = new PrintStream(new FileOutputStream(new File("application.log"), true));
        } catch (FileNotFoundException e) {
            System.out.println("file does not exist");
        }
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        fileStream.print("[" + level.name() + "]: ");
        fileStream.println(message);
    }
}
