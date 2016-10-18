package loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.loggers.PrintStreamLogger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class PrintStreamLoggerTest {

    private PrintStreamLogger logger;

    @Before
    public void setup(){
        logger = Mockito.mock(PrintStreamLogger.class);
    }

    @Test
    public void testLogMethod(){
        // vubec nevim, jestli to neni naprosta blbost to testovat takhle :-)
        logger.log(LogLevelEnum.ERROR, "Test Message");
        Mockito.verify(logger, Mockito.atLeast(1)).log(LogLevelEnum.ERROR, "Test Message");

    }

    @Test
    public void testCloseStream() throws IOException {
        logger.close();
        Mockito.verify(logger, Mockito.atLeast(1)).close();
    }


}
