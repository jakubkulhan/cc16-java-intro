package cz.codecamp.logger.loggers;

public class StdoutLogger extends PrintStreamLogger {

    public StdoutLogger() {
        super(System.out);
    }
}
