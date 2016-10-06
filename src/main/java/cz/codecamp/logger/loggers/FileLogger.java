package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by micha on 05.10.2016.
 */
public class FileLogger extends BaseLogger implements LoggerInterface {
    private static final String FORMAT_DATE = "yyyy-MM-dd";
    private LocalDateTime lastDateTime = null;
    private Writer writer = null;

    public FileLogger() {
    }

    @Override
    protected void logFormatted(LogLevelEnum level, String originalMessage, String formattedMessage) {
        try {
            getFileWriter().append(formattedMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        getFileWriter().close();
    }

    @Override
    public void setFormatter(FormatterInterface formatter) {
        super.setFormatter(formatter);
    }

    @Override
    public void setMinLogLevel(LogLevelEnum minLogLevel) {
        super.setMinLogLevel(minLogLevel);
    }

    private Writer getFileWriter() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        if (lastDateTime == null || now.getDayOfYear() != lastDateTime.getDayOfYear()) {
            lastDateTime = now;
            writer = new FileWriter("application_" + lastDateTime.format(DateTimeFormatter.ofPattern(FORMAT_DATE)) + ".log");
        }
        return writer;
    }
}
