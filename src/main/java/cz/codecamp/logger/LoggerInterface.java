package cz.codecamp.logger;

import java.io.Closeable;

public interface LoggerInterface extends Closeable {
    void log(LogLevelEnum level, String message);

    void setFormatter(FormatterInterface formatter);

    void setMinLogLevel(LogLevelEnum minLogLevel);
}
