package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LogMessage;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

public class PrintStreamLogger extends Logger implements LoggerInterface, PragmaticLoggerInterface, Closeable
{
    private PrintStream output;
    public PrintStreamLogger(PrintStream output) {
        this.output = output;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        if (level.getLevel() >= getBound().getLevel())
            output.println(new LogMessage(level, message, new Date(), JSON));
    }

    @Override
    public void close() throws IOException {
        if (output != null) {
            output.close();
        }
    }
}
