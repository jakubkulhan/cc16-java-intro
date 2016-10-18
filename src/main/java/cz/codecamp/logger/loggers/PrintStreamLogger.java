package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class PrintStreamLogger implements LoggerInterface {

    PrintStream fileLogger;

    public PrintStreamLogger(PrintStream fileLogger) {
        this.fileLogger = fileLogger;
    }

    public void closeStream() throws IOException {
        if(fileLogger != null) {
            fileLogger.flush();
            fileLogger.close();
        }
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        fileLogger.printf("[%s] %s\n", level.name(), message);
        try {
            closeStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
