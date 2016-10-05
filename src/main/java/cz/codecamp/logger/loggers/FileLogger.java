package cz.codecamp.logger.loggers;


import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileLogger implements LoggerInterface {

    private PrintStream stream;

    public FileLogger() {
        try {
            stream = new PrintStream(new FileOutputStream(new File("application.log")), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        stream.append(level.name() + " " + message);
        stream.append("\n");
    }
}
