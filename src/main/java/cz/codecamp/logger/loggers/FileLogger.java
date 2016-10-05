package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


/**
 * Created by lenka.salacova on 10/3/2016.
 */
public class FileLogger implements LoggerInterface {

    private PrintStream printStream;

    public FileLogger() {
        try {
            this.printStream = new PrintStream(new FileOutputStream(new File("application.log"), true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void log(LogLevelEnum level, String message) {
        this.printStream.printf("[%s]: %s\n", level.name(), message);
    }
}
