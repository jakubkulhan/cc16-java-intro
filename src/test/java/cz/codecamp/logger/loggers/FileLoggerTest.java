package cz.codecamp.logger.loggers;

import cz.codecamp.logger.formatters.DateFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by lenka.salacova on 10/16/2016.
 */
public class FileLoggerTest {

    FileLogger logger;
    DateFormatter formatter = new DateFormatter();
    String path = System.getProperty("user.home") + "/desktop//testlog";
    String toBeAppend = "logfilename";


    @Before
    public void setUp() throws Exception {
    logger = new FileLogger(path, formatter);
    }


    @Test
    public void getFileName() throws Exception {
    assertEquals(toBeAppend+".log", logger.getFileName(toBeAppend));
    }

    @Test
    public void createPrintStream() throws Exception {

        logger.createPrintStream(new File(path));

    }

    @After
    public void tearDown() throws Exception {

    }


}