package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

/**
 * Created by bmandzhi
 */
public class FileLoggerTest {

    PrintStream printStream;
    FileLogger fileLogger;
    LocalDateTime localDateTime = LocalDateTime.now();
    LogLevelEnum logLevelEnum;

    @Before
    public void setUp() {
        logLevelEnum = LogLevelEnum.WARNING;
        printStream = mock(PrintStream.class);
        fileLogger = new FileLogger(printStream);
    }

    @Test
    public void log1() throws Exception {
        fileLogger.log(logLevelEnum, localDateTime, anyString());
        verify(printStream, times(1)).println(localDateTime + " [" + logLevelEnum.name() + "] " + anyString());
    }

    @Test
    public void log2() throws Exception {
        fileLogger.log(anyString());
        verify(printStream, times(1)).println(anyString());
        verify(printStream, never()).println(localDateTime + "smth");
    }
}