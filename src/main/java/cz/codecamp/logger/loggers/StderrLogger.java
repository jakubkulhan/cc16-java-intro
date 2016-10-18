package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

public class StderrLogger extends PrintStreamLogger {
    public StderrLogger(FormatterInterface formater, LogLevelEnum threshold) {
        super(System.err, formater, threshold); //odkazu se na object system out a stvorim logger

    }
}
