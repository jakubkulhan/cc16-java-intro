package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;

public class StderrLogger extends PrintStreamLogger {
    public StderrLogger(FormatterInterface formater) {
        super(System.err, formater); //odkazu se na object system out a stvorim logger

    }
}
