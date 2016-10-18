package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by dasklar on 18. 10. 2016.
 */
public class FileLoggerTest {
    String message;
    LogLevelEnum log;
    FileLogger fileLogger;
    String nameOfFile;
    //private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        //System.setOut(new PrintStream(outContent));
        nameOfFile="application.log";
        fileLogger = new FileLogger(nameOfFile);
    message = "message text";
        log = LogLevelEnum.DEBUG;

    }

    @Test
    public void setLogger() throws Exception {
    fileLogger.setLogger(log,message,nameOfFile);
        assertEquals(fileLogger.getMessage(), message);
        assertEquals(fileLogger.getLevel(), log);
    }

    @Test
    public void getLevel() throws Exception {
        fileLogger.setLogger(log,message,nameOfFile);
        assertEquals(fileLogger.getLevel(), log);
    }

    @Test
    public void getMessage() throws Exception {
        fileLogger.setLogger(log,message,nameOfFile);
        assertEquals(fileLogger.getMessage(), message);
    }

    @Test
    public void log() throws Exception {
        final File expected = new File(nameOfFile);
        //final File actual = folder.newFile(nameOfFile);
        fileLogger.setLogger(log,message,nameOfFile);
        fileLogger.log(log,message);

        assertEquals(FileUtils.readLines(expected).toString(),"[[" + log + "] " + message + "]");
    }

    @After
    public void cleanUpStreams(){
        File toDelete = new File(nameOfFile);
        toDelete.delete();
        System.setOut(null);
    }

}