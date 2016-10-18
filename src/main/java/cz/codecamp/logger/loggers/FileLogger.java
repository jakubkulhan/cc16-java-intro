package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.*;

/**
 * Created by Lenovo on 03.10.2016.
 */
public class FileLogger implements LoggerInterface, PragmaticLoggerInterface, Closeable{
    private StringFormatter stringFormatter;
    private PrintStream fileStream;
    private MessageFilter messageFilter;

    public FileLogger() {
        messageFilter = new MessageFilter(LogLevelEnum.WARNING);
        stringFormatter = new StringFormatter(true);
        try {
            fileStream = new PrintStream(new FileOutputStream(new File("fileLog.log"), true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Log method implemented for LoggerInterface
     * @param level - logging level from logger.LogLevelEnum
     * @param message - String log message
     */
    @Override
    public void log(LogLevelEnum level, String message) {
        if(messageFilter.send(level)) {
            fileStream.printf("%s\n", stringFormatter.format(level, message));
        }
    }

    /**
     * Closes writing to current log file
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if(fileStream != null) {
            fileStream.flush();
            fileStream.close();
        }
    }

    /**
     * Default methods implemented for LoggerInterface
     */
    @Override
    public void debug(String message) {
        fileStream.printf("[%s]: %s\n", LogLevelEnum.DEBUG, message);
    }

    @Override
    public void info(String message) {
        fileStream.printf("[%s]: %s\n", LogLevelEnum.INFO, message);
     }

    @Override
    public void warning(String message) {
        fileStream.printf("[%s]: %s\n", LogLevelEnum.WARNING, message);
    }

    @Override
    public void error(String message) {
        fileStream.printf("[%s]: %s\n", LogLevelEnum.ERROR, message);
    }
}