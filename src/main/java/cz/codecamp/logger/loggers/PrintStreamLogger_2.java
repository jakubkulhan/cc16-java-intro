package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.PrintStream;

public class PrintStreamLogger_2 implements LoggerInterface {

    private PrintStream printStream;

    public PrintStreamLogger_2(PrintStream printStream){
        this.printStream = printStream;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        printStream.print(level.name() + " " + message);
    }

}
