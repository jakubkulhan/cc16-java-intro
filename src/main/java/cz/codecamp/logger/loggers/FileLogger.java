package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vkorecky on 4.10.16.
 */
public class FileLogger implements LoggerInterface, Closeable, PragmaticLoggerInterface {
    private PrintStream fileStream;

    public FileLogger() {
        try {

            fileStream = new PrintStream(new FileOutputStream(new File("application.log"), true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        fileStream.printf("[%s] [%s]: %s\n", format.format(new Date()), level.name(), message);
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     * <p>
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        if (fileStream != null){
            fileStream.flush();
            fileStream.close();
        }
    }
}
