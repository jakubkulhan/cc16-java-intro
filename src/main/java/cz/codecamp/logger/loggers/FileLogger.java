package cz.codecamp.logger.loggers;


import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

public class FileLogger implements PragmaticLoggerInterface {

    private PrintStream stream;

    public FileLogger() {
        try {
            stream = new PrintStream(new FileOutputStream(new File("application.log")), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        System.out.printf("[%s] [%s]: %s\n", level.name(), dt.format(new Date()), message);
    }

    @Override
    public void debug(String message) {
        log(LogLevelEnum.DEBUG, message);
    }

    @Override
    public void info(String message) {
        log(LogLevelEnum.INFO, message);
    }

    @Override
    public void warning(String message) {
        log(LogLevelEnum.WARNING, message);
    }

    @Override
    public void error(String message) {
        log(LogLevelEnum.ERROR, message);
    }
}
