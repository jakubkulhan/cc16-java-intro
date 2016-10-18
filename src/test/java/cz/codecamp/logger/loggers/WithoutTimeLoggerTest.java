package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dasklar on 18. 10. 2016.
 */
public class WithoutTimeLoggerTest {
    private WithoutTimeLogger withoutTimeLogger;
    private LogLevelEnum log;
    private String message;

    @Before
    public void setUp() throws Exception {
    withoutTimeLogger = new WithoutTimeLogger();
        log = LogLevelEnum.DEBUG;
        message = "debug message";
    }

    @Test
    public void format() throws Exception {
        assertEquals(withoutTimeLogger.format(log,message),"[" + log + "] [" + message + "]");
    }

}