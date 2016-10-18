package loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.loggers.StdoutLogger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class StdoutLoggerTest {

    private StdoutLogger logger;

    @Before
    public void setup(){
        logger = Mockito.mock(StdoutLogger.class);
    }

    @Test
    public void testLogMethod(){
        // vubec nevim, jestli to neni naprosta blbost to testovat takhle :-)
        logger.log(LogLevelEnum.ERROR, "Test Message");
        Mockito.verify(logger, Mockito.atLeast(1)).log(LogLevelEnum.ERROR, "Test Message");
    }

}
