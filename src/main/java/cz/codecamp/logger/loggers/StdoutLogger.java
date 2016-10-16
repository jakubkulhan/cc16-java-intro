package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.PrintStream;

public class StdoutLogger extends PrintStreamLogger {
    public StdoutLogger(FormatterInterface formater) {
        super(System.out, formater); //odkazu se na object system out a stvorim logger

    }
}
