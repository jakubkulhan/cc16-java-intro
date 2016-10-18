package cz.codecamp.logger.writers;

import cz.codecamp.logger.util.TimeProvider;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateBasedRotatingFileWriter implements LogMessageWriter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");

    private final String fileName;
    private LocalDateTime dateOfTheLastMessage;

    private PrintStream mainPrintStream;
    private PrintStream rotatingPrintStream;

    public DateBasedRotatingFileWriter(String fileName) {
        this.fileName = fileName;
        initializeStreams();
    }

    private void initializeStreams() {
        try {
            dateOfTheLastMessage = TimeProvider.getInstance().getTime();
            mainPrintStream = new PrintStream(new FileOutputStream(fileName, false));
            rotatingPrintStream = new PrintStream(new FileOutputStream(getRotatedName(fileName), true));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String getRotatedName(String fileName) {
        return fileName + "." + dateOfTheLastMessage.format(formatter);
    }

    @Override
    public void log(String logMessage) {
        if (dateHasChanged()) {
            initializeStreams();
        }
        mainPrintStream.print(logMessage);
        rotatingPrintStream.print(logMessage);
    }

    private boolean dateHasChanged() {
        LocalDateTime currentDate = TimeProvider.getInstance().getTime();
        return currentDate.getDayOfYear() != dateOfTheLastMessage.getDayOfYear() || currentDate.getYear() != dateOfTheLastMessage.getYear();
    }
}
