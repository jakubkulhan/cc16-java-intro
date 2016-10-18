package cz.codecamp.logger.loggers;

import cz.codecamp.logger.ApplicationConst;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.SingleFormatter;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by honzapua on 18.10.2016.
 */
public class PrintStreamLoggerTest {

    @Test
    public void isLoggingToStream() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);

        //
        LoggerInterface logger = new PrintStreamLogger(ps, new SingleFormatter(), LogLevelEnum.DEBUG);
        logger.log(LogLevelEnum.DEBUG, "Test message");

        ps.flush();
        String actual = baos.toString();
        String expected = String.format("[DEBUG] Test message%s", ApplicationConst.EOL_SEQUENCE);

        Assert.assertEquals(expected, actual);
    }
}
