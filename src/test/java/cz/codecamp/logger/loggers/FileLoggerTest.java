package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.formaters.DummyFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileLoggerTest {
    private ByteArrayOutputStream out;
    private FileLogger logger;
    private DummyFormatter formatter;
    private File logFile;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException{
        logFile = folder.newFile("application.log");
        String filename = logFile.getPath();
        logger = new FileLogger(filename);
        formatter = new DummyFormatter();
        logger.setFormatter(formatter);
    }

    @After
    public void cleanUp() {
        logger = null;
        formatter = null;
        logFile = null;
    }

    @Test
    public void logsCorrect() throws FileNotFoundException {
        logger.log(LogLevelEnum.INFO, "test");
        String content = new Scanner(logFile).useDelimiter("\\Z").next();
        assertEquals("test", content);
    }
}
