package cz.codecamp.logger.loggers;


import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

public class PrintStreamLogger implements LoggerInterface, Closeable, PragmaticLoggerInterface {

    private PrintStream printStream;

    public PrintStreamLogger(PrintStream printStream) {
        this.printStream = printStream;
        initMapOfLevels();
    }

    @Override
    public void log(LogLevelEnum level, String message, int minimumLevel) {
        if (mapOfLevels.get(level) >= minimumLevel) {
            printStream.printf("[%s] [%s]: %s\n", format.format(new Date()), level.name(), message);
        }
    }

    @Override
    public void close() throws IOException {
        if (printStream != null) {
            printStream.flush();
            printStream.close();
        }
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void initMapOfLevels(){
        mapOfLevels.put(LogLevelEnum.DEBUG,1);
        mapOfLevels.put(LogLevelEnum.INFO,2);
        mapOfLevels.put(LogLevelEnum.WARNING,3);
        mapOfLevels.put(LogLevelEnum.ERROR,4);
    }

}