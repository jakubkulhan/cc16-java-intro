package cz.codecamp.logger;

public interface LoggerInterface {

    void log(LogLevelEnum level, String message);

    LogLevelEnum getThreshold();

    void setThreshold(LogLevelEnum threshold);
}
