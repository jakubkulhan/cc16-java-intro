package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by dasklar on 18. 10. 2016.
 */
public class StdoutLoggerTest {
    StdoutLogger stdoutLogger;
    LogLevelEnum log;
    String message;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
    stdoutLogger = new StdoutLogger();
        log = LogLevelEnum.INFO;
        message = "info test";
    }

    @Test
    public void log() throws Exception {
        stdoutLogger.log(log,message);
    assertEquals(outContent.toString(),"[" + log + "] [" + message+"]\n");
    }



    void printMes(){
        //System.out.printf("[%s]: %s\n", withoutTimeLogger.format(level, message));
    }



}