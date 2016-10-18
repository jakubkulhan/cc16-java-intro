package cz.codecamp.logger.loggers;

import cz.codecamp.logger.ApplicationConst;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.SingleFormatter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by honzapua on 18.10.2016.
 */
abstract class MultiLoggerTestSupport {

    private ByteArrayOutputStream baosLogger1;
    private ByteArrayOutputStream baosLogger2;
    private PrintStream psLogger1;
    private PrintStream psLogger2;

    //protocol pro potomky
    abstract protected MultiLogger getMultiLogger() throws Exception;

    @Test
    public void isMultiloggerLogingTwoLogs() throws Exception {
        MultiLogger logger = getMultiLogger();
        logger.log(LogLevelEnum.DEBUG, "XXX");
        String expected = String.format("[DEBUG] XXX%s", ApplicationConst.EOL_SEQUENCE);
        testEquals(expected);
    }


    @Before
    public void setUp() throws Exception{
        baosLogger1 = new ByteArrayOutputStream();
        baosLogger2 = new ByteArrayOutputStream();
        psLogger1 = new PrintStream(baosLogger1);
        psLogger2 = new PrintStream(baosLogger2);
    }

    @After
    public void tearDown() throws  Exception {
        psLogger2 = null;
        psLogger1 = null;
        baosLogger2 = null;
        baosLogger1 = null;
    }

    private void testEquals(String expected) {
        psLogger1.flush();
        psLogger2.flush();
        String actual = baosLogger1.toString();
        Assert.assertEquals("Logger 1", expected, actual);
        actual = baosLogger2.toString();
        Assert.assertEquals("Logger 2", expected, actual);
    }

    protected PrintStream getPsLogger1() {
        return psLogger1;
    }

    protected PrintStream getPsLogger2() {
        return psLogger2;
    }

    protected LoggerInterface createPrintStreamLogger(PrintStream stream) throws Exception {
        return new PrintStreamLogger(stream, new SingleFormatter(), LogLevelEnum.DEBUG);
    }

}
