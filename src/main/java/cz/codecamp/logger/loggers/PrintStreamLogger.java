package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.Message;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintStreamLogger extends LoggerAbstract {

    protected PrintStream printStream;

    public PrintStreamLogger(PrintStream printStream) {
        super();
        this.printStream = printStream;
    }

    protected void write(LogLevelEnum level, Message message) {
        printStream.println(formatter.format(message));
    }
}
