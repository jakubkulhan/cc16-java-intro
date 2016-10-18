package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by dasklar on 18. 10. 2016.
 */
public class PrintStreamLoggerTest {
    PrintStreamLogger printStreamLogger;
    LogLevelEnum log;
    String message;
    String nameOfFile;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        nameOfFile = "printStream.log";
        System.setOut(new PrintStream(outContent));
        printStreamLogger = new PrintStreamLogger(new PrintStream(new FileOutputStream(new File(nameOfFile),true)));
        log = LogLevelEnum.INFO;
        message = "[info test]";
    }

    @Test
    public void log() throws Exception {
        final File expected = new File(nameOfFile);
        printStreamLogger.log(log,message);
        assertEquals(FileUtils.readLines(expected).toString(),"[[" + log + "] " + message+"]");
    }

    @After
    public void cleanUpStreams(){
        File toDelete = new File(nameOfFile);
        toDelete.delete();
        System.setOut(null);
    }

}