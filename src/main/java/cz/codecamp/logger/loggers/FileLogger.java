package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class FileLogger implements PragmaticLoggerInterface {

    private PrintStream fileStream;

    public FileLogger() {
        try {
            this.fileStream = new PrintStream(new FileOutputStream(new File("application_" + LocalDateTime.now().getDayOfMonth() + "_" + LocalDateTime.now().getMonth() + ".log"), true));
        } catch (FileNotFoundException e) {
            System.out.println("file does not exist");
        }
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        newLogFilePerDay();
        if (level.equals(LogLevelEnum.WARNING) || level.equals(LogLevelEnum.ERROR))
            fileStream.println(LocalDateTime.now() + " [" + level.name() + "] " + message);
    }

    @Override
    public void log(LogLevelEnum level, LocalDateTime time, String message) {
        newLogFilePerDay();
        if (level.equals(LogLevelEnum.WARNING) || level.equals(LogLevelEnum.ERROR))
            fileStream.println("\n" + time + " [" + level.name() + "] " + message);
    }

    //log in JSON format
    @Override
    public void log(String message) {
        newLogFilePerDay();
        fileStream.println(message);
    }

    @Override
    public void close() {
        fileStream.close();
    }

    private void newLogFilePerDay() {
        File f = new File("application_" + LocalDateTime.now().getDayOfMonth() + "_" + LocalDateTime.now().getMonth() + ".log");
        if (!f.exists()) {
            try {
                fileStream = new PrintStream(new FileOutputStream(f, true));
            } catch (FileNotFoundException e) {
                System.out.println("file does not exist");
            }
        }
    }
}
