package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.ObjectToLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

/**
 * Created by bmandzhi
 */
public class FileLoggerTest {

    @Mock
    ObjectToLog objectToLog;

    FileLogger fileLogger;


    PrintStream fileStream;

    LocalDateTime dateTime = LocalDateTime.now();

    @Parameterized.Parameters
    public static String[] data() {
        return new String[]{"first test", "second test"};
    }

    @Before
    public void setUp() {
        fileLogger = mock(FileLogger.class);
        fileStream = mock(PrintStream.class);
    }

    @Test
    public void log() throws Exception {
        fileLogger.log(LogLevelEnum.WARNING, "first test");
        fileLogger.log(LogLevelEnum.INFO, "info");
        verify(fileLogger, times(1)).log(LogLevelEnum.WARNING, "first test");
        verify(fileStream, never()).println(dateTime + " [" + LogLevelEnum.INFO + "] " + "info");
    }

    @Test
    public void log1() throws Exception {
        fileLogger.log(LogLevelEnum.WARNING, dateTime, "first test");
        verify(fileLogger, times(1)).log(LogLevelEnum.WARNING, dateTime, "first test");
    }

    @Test
    public void log2() throws Exception {
        fileLogger.log("first test");
        verify(fileLogger, times(1)).log("first test");
    }
}