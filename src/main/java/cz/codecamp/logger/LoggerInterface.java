package cz.codecamp.logger;

import java.io.Closeable;
import java.util.function.Supplier;

public interface LoggerInterface extends Closeable {
    void log(LogLevelEnum level, String message);

    void setFormatter(FormatterInterface formatter);

    void setMinLogLevel(LogLevelEnum minLogLevel);

    void setTimeSupplier( Supplier<Long> timeSupplier);
}
