package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.formatters.DateFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by lenka.salacova on 10/16/2016.
 */
public class DayLoggerTest {

    String path = System.getProperty("user.home") + "/desktop//testlog";
    DateFormatter formatter = new DateFormatter();
    DayLogger logger;
    String[] message = {"Test Message 1", "TestMessage 2"};
    LogLevelEnum[] levelEnum = {LogLevelEnum.INFO, LogLevelEnum.WARNING};

    @Before
    public void setUp() throws Exception {

        logger = new DayLogger(path, formatter);



    }

    @Test
    public void log() throws Exception {

        for (int i = 0; i < message.length; i++) {
            logger.log(levelEnum[i], message[i]);
        }

        logger.close();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String expectedPath = path + "_" + LocalDateTime.now().format(dateFormat)+".log";

        Scanner scanner = new Scanner(new File(expectedPath));

        int i = 0;
        for ( ; scanner.hasNextLine(); i++) {
            assertEquals(formatter.format(levelEnum[i], message[i]), scanner.nextLine());
        }

        assertTrue("Some rows are not in target file",i == levelEnum.length);
        scanner.close();

    }



    @After
    public void tearDown() throws Exception {

    }





}