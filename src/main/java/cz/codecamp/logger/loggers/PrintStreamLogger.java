package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintStreamLogger implements LoggerInterface, PragmaticLoggerInterface {

    protected PrintStream printStream;

    public PrintStreamLogger(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        String ts = new SimpleDateFormat("yyyy-mm-dd hh:mm").format(new Date());
        this.printStream.printf("[%s] [%s]: %s\n", level.name(), ts, message);
    }

}
