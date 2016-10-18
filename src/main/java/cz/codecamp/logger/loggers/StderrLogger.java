package cz.codecamp.logger.loggers;

public class StderrLogger extends PrintStreamLogger {

    public StderrLogger() {
        super(System.err);
    }
}
