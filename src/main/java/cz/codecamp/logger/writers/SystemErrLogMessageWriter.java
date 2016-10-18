package cz.codecamp.logger.writers;

public class SystemErrLogMessageWriter implements LogMessageWriter {
    @Override
    public void log(String logMessage) {
        System.err.print(logMessage);
    }
}
