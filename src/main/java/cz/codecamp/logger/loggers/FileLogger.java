package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LogMessage;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;
import java.io.*;
import java.nio.file.Path;
import java.util.Date;

public class FileLogger extends Logger implements LoggerInterface, PragmaticLoggerInterface, Closeable
{
    private Path path;
    private PrintStream fileStream;
    public FileLogger(Path path) {
        this.path = path;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        if (level.getLevel() >= getBound().getLevel()) {
            try {
                fileStream = new PrintStream(
                        new FileOutputStream(
                                new File(path.toString()), true)
                );
                fileStream.println(new LogMessage(level, message, new Date(), JSON));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (fileStream != null) {
            fileStream.close();
        }
    }
}
