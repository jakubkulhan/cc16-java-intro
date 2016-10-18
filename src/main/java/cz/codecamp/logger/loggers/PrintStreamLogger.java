package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.PrintStream;

public class PrintStreamLogger implements PragmaticLoggerInterface {

    private PrintStream printStream;

    public PrintStreamLogger(PrintStream printStream){
        this.printStream = printStream;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        printStream.print(formatter.format(level, message));
    }

    public void close(){
        printStream.close();
    }

}
