package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger implements LoggerInterface, PragmaticLoggerInterface {

    @Override
    public void log(LogLevelEnum level, String message) {
        try {
            PrintStream stream = new PrintStream(new FileOutputStream(new File("application.log"), true));
            String ts = new SimpleDateFormat("yyyy-mm-dd hh:mm").format(new Date());
            stream.printf("[%s] [%s]: %s\n", level.name(), message);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

}
