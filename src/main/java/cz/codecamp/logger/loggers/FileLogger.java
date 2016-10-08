package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import java.io.*;
import java.net.URI;

/**
 * Created by vkorecky on 4.10.16.
 */
public class FileLogger extends AbstractLogger implements Closeable {

    private PrintStream fileStream;

    public FileLogger(URI logFilePath) {
        super();
        try {
            fileStream = new PrintStream(new FileOutputStream(new File(logFilePath), true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logger with custom formatter
     *
     * @param formatter
     */
    public FileLogger(FormatterInterface formatter, PrintStream fileStream) {
        super(formatter);
        this.fileStream = fileStream;
    }

    /**
     *
     * @param level
     * @param message
     */
    @Override
    public void internalLog(LogLevelEnum level, String message, String callersClassName, String callersMethodName, int callersLineNumber) {
        fileStream.println(format(level, message, callersClassName, callersMethodName, callersLineNumber));
    }

    /**
     * Closes this stream and releases any system resources associated with it.
     * If the stream is already closed then invoking this method has no effect.
     * <p>
     * <p>
     * As noted in {@link AutoCloseable#close()}, cases where the close may fail
     * require careful attention. It is strongly advised to relinquish the
     * underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing the
     * {@code IOException}.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        if (fileStream != null) {
            fileStream.flush();
            fileStream.close();
        }
    }
}
