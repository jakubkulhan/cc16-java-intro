package cz.codecamp.logger.writers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SimpleLogMessageFileWriter implements LogMessageWriter {

    private PrintStream fileStream;

    public SimpleLogMessageFileWriter(String fileName) {
        try {
            fileStream = new PrintStream(new FileOutputStream(fileName, true));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void log(String logMessage) {
        fileStream.print(logMessage);
        fileStream.flush();
    }
}
