package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.PrintStream;

/**
 * Created by lenka.salacova on 10/5/2016.
 */
public class PrintStreamLogger extends AbstractLogger {

    private PrintStream printStream;
    private FormatterInterface formatter;

    public PrintStreamLogger(PrintStream printStream, FormatterInterface formatter) {
        this.printStream = printStream;
        this.formatter = formatter;
    }



    @Override
    public void log(LogLevelEnum level, String message) {
//        this.printStream.printf("[%s]: %s\n", level.name(), message);
        printStream.println(formatter.format(level, message));
    }
}
