package cz.codecamp.logger;

public interface LoggerInterface {
    void log(LogLevelEnum level, String message);
    void log(String level, String message);

    void debug(String content);
    void debug(String content, boolean stdOut);
    void info(String content);
    void info(String content, boolean stdOut);
    void error(String content);
    void error(String content, boolean stdOut);
    void warning(String content);
    void warning(String content, boolean stdOut);

    void system(String content);
}