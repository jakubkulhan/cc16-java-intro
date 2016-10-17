package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * Created by lenka.salacova on 10/16/2016.
 */
public class DateFormatterTest {
    DateFormatter dateFormatter;
    String testMessage = "TestMessage";
    LogLevelEnum levelEnum = LogLevelEnum.DEBUG;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @Before
    public void setUp() throws Exception {
        dateFormatter = new DateFormatter();
    }

    @Test
    public void format() throws Exception {
        String output = dateFormatter.format(levelEnum, testMessage);
        assertEquals("[" + levelEnum + "] [" + LocalDateTime.now().format(dateFormat) + "] " + testMessage, output);
    }


}

