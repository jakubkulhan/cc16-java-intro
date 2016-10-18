package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.Message;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger extends PrintStreamLogger {
    private File file;

    public FileLogger(String filename) throws FileNotFoundException{
        // init will null, if will take care of print stream
        super(null);
        initialize(filename);
    }

    private void initialize(String filename) throws FileNotFoundException {
        file = new File(filename);
        printStream = new PrintStream(new FileOutputStream(file, true));
    }
}
