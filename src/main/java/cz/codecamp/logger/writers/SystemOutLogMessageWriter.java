package cz.codecamp.logger.writers;

public class SystemOutLogMessageWriter implements LogMessageWriter {
    @Override
    public void log(String logMessage) {
        System.out.print(logMessage);
    }
}
