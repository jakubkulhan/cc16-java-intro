package cz.codecamp.logger.loggers;

import cz.codecamp.logger.PragmaticLoggerInterface;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

/**
 * Created by bmandzhi
 */
public class MultiLoggerTest {

    FileLogger fileLogger;

    StdoutLogger stdoutLogger;

    List<PragmaticLoggerInterface> loggers;

    MultiLogger multiLogger;

    @Before
    public void setUp() {
        fileLogger = mock(FileLogger.class);
        stdoutLogger = mock(StdoutLogger.class);
        loggers = new ArrayList<>();
        loggers.add(fileLogger);
        loggers.add(stdoutLogger);
        multiLogger = new MultiLogger(loggers);
    }

    @Test
    public void log() throws Exception {
        multiLogger.log(anyString());
        Mockito.verify(fileLogger, times(1)).log(anyString());
        Mockito.verify(stdoutLogger, times(1)).log(anyString());
    }

    @Test
    public void log1() throws Exception {
        multiLogger.log(anyObject(), anyString());
        Mockito.verify(fileLogger, times(1)).log(anyObject(), anyString());
        Mockito.verify(stdoutLogger, times(1)).log(anyObject(), anyString());
    }

    @Test
    public void log2() throws Exception {
        multiLogger.log(anyObject(), anyObject(), anyString());
        Mockito.verify(fileLogger, times(1)).log(anyObject(), anyObject(), anyString());
        Mockito.verify(stdoutLogger, times(1)).log(anyObject(), anyObject(), anyString());
    }
}