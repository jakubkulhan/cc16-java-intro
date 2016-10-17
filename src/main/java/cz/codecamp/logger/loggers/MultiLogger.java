package cz.codecamp.logger.loggers;


import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by lenka.salacova on 10/5/2016.
 */
public class MultiLogger extends AbstractLogger {

    private LoggerInterface[] loggerInterfaces;

    public MultiLogger(LoggerInterface[] loggerInterfaces) {
        this.loggerInterfaces = loggerInterfaces;

    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for (LoggerInterface logger: loggerInterfaces) {
            logger.log(level, message);
        }
    }


}
