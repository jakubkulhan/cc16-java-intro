package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by vkorecky on 4.10.16.
 */
public class PrintStreamLogger implements LoggerInterface, Closeable {
    private PrintStream stream;

    public PrintStreamLogger(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        stream.printf("[%s]: %s\n", level.name(), message);
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
        if (stream != null){
            stream.flush();
            stream.close();
        }
    }
}
