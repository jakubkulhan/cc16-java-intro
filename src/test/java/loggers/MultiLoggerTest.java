package loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.loggers.FileLogger;
import cz.codecamp.logger.loggers.MultiLogger;
import cz.codecamp.logger.loggers.PrintStreamLogger;
import cz.codecamp.logger.loggers.StdoutLogger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MultiLoggerTest {

    private MultiLogger multiLogger;

    private FileLogger fileLogger;

    private PrintStreamLogger printStreamLogger;

    private StdoutLogger stdoutLogger;

    @Before
    public void setup(){

        fileLogger = Mockito.mock(FileLogger.class);
        printStreamLogger = Mockito.mock(PrintStreamLogger.class);
        stdoutLogger = Mockito.mock(StdoutLogger.class);
        multiLogger = new MultiLogger(fileLogger, printStreamLogger, stdoutLogger);

    }

    @Test
    public void testCallAllLoggersInMultiLogger(){

        // vubec nevim, jestli to neni naprosta blbost to testovat takhle :-)
        multiLogger.log(LogLevelEnum.DEBUG, "Test Message");
        Mockito.verify(printStreamLogger, Mockito.atLeast(1)).log(LogLevelEnum.DEBUG, "Test Message");
        Mockito.verify(fileLogger, Mockito.atLeast(1)).log(LogLevelEnum.DEBUG, "Test Message");
        Mockito.verify(stdoutLogger, Mockito.atLeast(1)).log(LogLevelEnum.DEBUG, "Test Message");

    }

}
