package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by blaha on 06.10.2016.
 */
public class StdoutLoggerTest {
    @Test
    public void logFormatted() throws Exception {
        StdoutLogger logger = new StdoutLogger();
        logger.log(LogLevelEnum.INFO, "Test message");
    }

    @Test
    public void close() throws Exception {

    }

}