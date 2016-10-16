package cz.codecamp.logger.loggers;

import cz.codecamp.logger.*;
import cz.codecamp.logger.fileHandlers.DayBasedFileHandler;
import cz.codecamp.logger.fileHandlers.FileHandler;
import cz.codecamp.logger.fileHandlers.IntervalBasedFileHandler;
import cz.codecamp.logger.formatters.LogFormatter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.temporal.ChronoUnit;

public class FileLogger extends Logger implements LoggerInterface, PragmaticLoggerInterface {

    private String fileName = "app.log";
    private PrintStream fileStream;
    private FileHandler fileHandler;
    private FormatterInterface defaultFormatter;

    public FileLogger() {

        fileHandler = new IntervalBasedFileHandler(fileName, ChronoUnit.MINUTES, 1);
        //fileHandler = new DayBasedFileHandler(fileName);
        defaultFormatter = new LogFormatter();
    }

    public FileLogger(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        defaultFormatter = new LogFormatter();
    }

    private void adjustFileStream() {
        if (fileHandler.hasLogFileLifeExpired()) {
            if (fileStream!=null) {
                fileStream.close();
            }
            try {
                fileStream = new PrintStream( new FileOutputStream(fileHandler.getLogFile(), true ));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        if (level.ordinal() >= getMinLogLevel().ordinal()) {
            adjustFileStream();
            fileStream.println(defaultFormatter.format(level, message));
        }
    }

    @Override
    public void log(LogLevelEnum level, String message, FormatterInterface formatter) {
        if (level.ordinal() >= getMinLogLevel().ordinal()) {
            adjustFileStream();
            fileStream.println(formatter.format(level, message));
        }
    }

    public void setLogsFolder(String logsFolder) {
        fileHandler.setLogsFolder(logsFolder);
        if (fileStream!=null) {
            fileStream.close();
        }

        try {
            fileStream = new PrintStream( new FileOutputStream(fileHandler.getLogFile(), true ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getLogsFolder() {
        return fileHandler.getLogsFolder();
    }
}
