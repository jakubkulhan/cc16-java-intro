package cz.codecamp.logger;

public interface LoggerInterface {
    void log(LogLevelEnum level, String message);
    void write(String message);
}
